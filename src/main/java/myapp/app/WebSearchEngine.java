package myapp.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.collections.List;
import myapp.searchengine.Index;
import myapp.searchengine.TreeNode;
import myapp.searchengine.WordWeigth;

public class WebSearchEngine {
    private final Index index;
    private ServerSocket serverSocket;

    public WebSearchEngine(String path, int tcpPort) throws IOException {
        index = new Index();
        index.build(path);
        serverSocket = new ServerSocket(tcpPort);

        // Boucle principale du serveur
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                // Démarrer un nouveau thread pour chaque connexion
                new Thread(new ClientHandler(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Classe interne pour gérer chaque client dans un thread séparé
    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                String requestLine = in.readLine();

                if (requestLine != null && requestLine.startsWith("GET")) {
                    String[] tokens = requestLine.split(" ");
                    if (tokens.length >= 2) {
                        String url = tokens[1];

                        if (url.startsWith("/search")) {
                            handleSearchRequest(url, out);
                        } else {
                            send404(out);
                        }
                    }
                } else if (requestLine != null && requestLine.startsWith("POST")) {
                    send404(out);
                }

                while (!in.readLine().isEmpty()) {
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleSearchRequest(String path, PrintWriter out) {
            try {
                String queryParam = getQueryParam(path, "q");
                if (queryParam == null || queryParam.isEmpty()) {
                    send400(out, "Paramètre 'q' manquant ou vide");
                    return;
                }

                TreeNode result = index.find(queryParam);
                if (result != null) {
                    send200(out, result.getFilesList());
                } else {
                    send400(out, "Aucun fichier trouvé pour le mot: " + queryParam);
                }
            } catch (Exception e) {
                send500(out, e.getMessage());
            }
        }

        private String getQueryParam(String path, String param) {
            if (path.contains("?")) {
                String[] parts = path.split("\\?");
                if (parts.length > 1) {
                    String[] params = parts[1].split("&");
                    for (String p : params) {
                        String[] keyValue = p.split("=");
                        if (keyValue.length == 2 && keyValue[0].equals(param)) {
                            return keyValue[1];
                        }
                    }
                }
            }
            return null;
        }

        private void send200(PrintWriter out, List<WordWeigth> results) throws EndOfListException {
            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: application/json; charset=UTF-8\r\n");
            out.write("\r\n");
            out.write("{[\n");
            Iterator<WordWeigth> iterator = new Iterator<>(results);
            while (iterator.hasNext()) {
                var next = iterator.next();
                out.write("{\"filename\": \"" + next.getFilename() + "\", \"weight\": \"" + next.getWeight() + "\"");
                if (iterator.hasNext()) {
                    out.write(",\n");
                }
            }
            out.write("\n]}\n");
            out.flush();
        }

        private void send400(PrintWriter out, String message) {
            out.write("HTTP/1.1 400 Bad Request\r\n");
            out.write("Content-Type: text/plain; charset=UTF-8\r\n");
            out.write("\r\n");
            out.write("Erreur: " + message + "\n");
            out.flush();
        }

        private void send404(PrintWriter out) {
            out.write("HTTP/1.1 404 Not Found\r\n");
            out.write("Content-Type: text/plain; charset=UTF-8\r\n");
            out.write("\r\n");
            out.write("Erreur 404: La page demandée n'existe pas\n");
            out.flush();
        }

        private void send500(PrintWriter out, String message) {
            out.write("HTTP/1.1 500 Internal Server Error\r\n");
            out.write("Content-Type: text/plain; charset=UTF-8\r\n");
            out.write("\r\n");
            out.write("Erreur 500: Problème interne du serveur\n");
            if (message != null) {
                out.write("Détails: " + message + "\n");
            }
            out.flush();
        }
    }
}

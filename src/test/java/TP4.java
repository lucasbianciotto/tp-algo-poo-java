import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

public class TP4 {

    @Test
    public void exo1() throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8282);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /hello/world/! HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 200 OK", in.readLine());
        Assertions.assertEquals("Content-Type: text/plain; charset=UTF-8", in.readLine());
        Assertions.assertEquals("", in.readLine());
        Assertions.assertEquals("Request : GET /hello/world/!", in.readLine());
        socket.close();
    }

    @Test
    public void exo2() throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8282);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /search?q=vente HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 200 OK", in.readLine());
        Assertions.assertEquals("Content-Type: text/plain; charset=UTF-8", in.readLine());
        Assertions.assertEquals("", in.readLine());
        Assertions.assertEquals("textes/code_civil/563.txt", in.readLine());
        Assertions.assertEquals("textes/code_de_commerce/L441-2.txt", in.readLine());
        in.readLine();
        in.readLine();
        Assertions.assertEquals("textes/code_de_la_propriete_intellectuelle/L122-5.txt",
        in.readLine());
        socket.close();
        socket = new Socket("localhost", 8282);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /search?b=vente HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 400 Bad Request", in.readLine());
        socket.close();
        socket = new Socket("localhost", 8282);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /hello?q=a HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 404 Not Found", in.readLine());
        socket.close();
        socket = new Socket("localhost", 8282);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /search HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 400 Bad Request", in.readLine());
        socket.close();
        socket = new Socket("localhost", 8282);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("POST /hello HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 404 Not Found", in.readLine());
        socket.close();
    }

    @Test
    public void exo3() throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8282);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /search?q=vente HTTP/1.1\n\r");
        out.write("\r\n");
        Assertions.assertEquals("HTTP/1.1 200 OK", in.readLine());
        Assertions.assertEquals("Content-Type: application/json; charset=UTF-8", in.readLine());
        Assertions.assertEquals("", in.readLine());
        Assertions.assertEquals("[", in.readLine());
        Assertions.assertEquals("\"textes/code_civil/563.txt\",", in.readLine());
        Assertions.assertEquals("\"textes/code_de_commerce/L441-2.txt\",", in.readLine());
        Assertions.assertEquals("\"textes/code_de_l_environnement/L411-1.txt\",",
        in.readLine());
        socket.close();
    }
    
}

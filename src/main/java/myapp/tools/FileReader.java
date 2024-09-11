package myapp.tools;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileReader {
    private java.io.InputStreamReader f;
    private int last;

    /*
    * Open a text file using the UNICODE encoding
    * fileName : path of the file
    * Terminate the application on error
    */
    public FileReader(String fileName) {
        try {
            f = new java.io.FileReader(fileName);
            last = f.read();
        } catch (FileNotFoundException e) {
            System.err.format("Error opening file %s : %s", fileName, e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.format("Error reading file %s : %s", fileName, e.getMessage());
            System.exit(1);
        }
    }

    /*
    * Open a text file using the given encoding
    * fileName : path of the file
    * charset : the character encoding of the file
    * Terminate the application on error
    */
    public FileReader(String fileName, Charset charset) {
        try {
            f = new java.io.FileReader(fileName, charset);
            last = f.read();
        } catch (FileNotFoundException e) {
            System.err.format("Error opening file %s : %s", fileName, e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.format("Error reading file %s : %s", fileName, e.getMessage());
            System.exit(1);
        }
    }

    /*
    * Return the current character from the stream and read the next character
    * returns : the current character. 
    * Terminate the application on error
    */
    public char readChar() {
        if (last == -1) {
            System.err.println("Reading past end of file");
            System.exit(1);
        }
        int r = last;
        try {
            last = f.read();
        } catch (IOException e) {
            System.err.format("Error reading character : %s", e.getMessage());
            System.exit(1);
        }
        return (char) r;
    }

    /*
    * Indicate if we reached the end od the stream
    * return true if all the characters have been read
    */
    public boolean isEndOfFile() {
        return last == -1;
    }

    /*
    * Create a FileReader stream from any InputStreamReader
    * Terminate the application on error
    */
    public FileReader(InputStreamReader is) {
        try {
            f = is;
            last = f.read();
        } catch (IOException e) {
            System.err.format("Error reading stream %s : %s", e.getMessage());
            System.exit(1);
        }
    }

    /*
    * Create a FileReader stream from an UNICODE string
    * Terminate the application on error
    */
    public static FileReader fromString(String content) {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream( content.getBytes(StandardCharsets.UTF_8)) );;
        return new FileReader(isr);
    }

}

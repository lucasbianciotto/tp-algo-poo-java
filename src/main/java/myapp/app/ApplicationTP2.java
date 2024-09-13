package myapp.app;

import java.io.IOException;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.collections.List;
import myapp.tools.FileReader;
import myapp.tools.TextFileTools;

public class ApplicationTP2 {
    
    public static void exo3(FileReader fileReader) throws EndOfListException {
        var file = TextFileTools.readFile(fileReader);
        var it = new Iterator<>(file);

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void exo4(String path) throws IOException, EndOfListException {
        Iterator<List<String>> file = new Iterator<>(TextFileTools.readDirectory(path));
        while (file.hasNext()) {
            System.out.println("---------------------------");
            Iterator<String> word = new Iterator<>(file.next());
            while (word.hasNext()) {
                System.out.format("%s ", word.next());
            }
            System.out.println();
       }
    }

}

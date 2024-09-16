package myapp.app;

import java.io.IOException;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.searchengine.Index;

public class ApplicationTP3 {

    public static void exo2(String path) throws IOException {
        Index index = new Index();
        index.build(path);
        index.walk(node -> {
            var count = 0;
            Iterator<String> files = new Iterator<>(node.getFilesList());
            while (files.hasNext()) {
                count++;
                try {
                    files.next();
                } catch(EndOfListException e) {
                    break;
                }
            }
            System.out.format("The word %s is used %d times\n", node.getWord(), count);
        });
    }

    public static void exo3(String path, String word) throws IOException {
        Index index = new Index();
        index.build(path);
        var result = index.find(word);
        if (result != null) {
            System.out.println(result.getWord() + " is in the list ");
        } else {
            System.out.println(word + " isn't in the list ");
        }
    }
    
}

package myapp.app;

import myapp.app.ApplicationTP1.DisplayFileName;
import myapp.collections.EndOfListException;
import myapp.collections.ListOfStockable;
import myapp.collections.Stockable;
import myapp.tools.FileReader;
import myapp.tools.ProcessFile;
import myapp.tools.TextFileTools;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {

    public static class StringData implements Stockable {
        final String message;
        StringData(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return  message;
        }
        @Override
        public boolean equals(Object obj) {
            if (message == null) return false;
            return message.equals(((StringData)obj).message);
        }
    }

    public static void main(String[] args) throws IOException {
        /* var filename = args[0];
        FileReader fileReader = new FileReader(filename); */
        /* ApplicationTP1 application = new ApplicationTP1(); */
        /* var currentChar = application.readChar(fileReader);
        if (currentChar != null) {
            System.out.println(currentChar);
        } else {
            System.out.println("Le fichier est vide!");
        } */
        // System.out.format("Dans le fichier il y a %s fois 'al'\n", application.countAl(fileReader));
        /* System.out.format("Dans le fichier il y a %d mots", application.countWords(fileReader)); */
        /* System.out.println(Arrays.toString(application.read5Words(fileReader))); */
        // System.out.println(Arrays.toString(application.countPunctuations(fileReader)));
        /* System.out.format("Il y a %d fois 'et'", TextFileTools.countWord(fileReader, "et")); */
        /* System.out.println(TextFileTools.readWord(fileReader)); */
        /* application.displayDirectoryContent("textes"); */
        /* application.displayFilesWithWord("textes", "conformément"); */

       /*  try {
            ApplicationTP2.exo3(fileReader);
            ApplicationTP2.exo4("textes");
        } catch (EndOfListException e) {
            e.printStackTrace();
        } */

       /* ApplicationTP3.exo2("textes"); */
       /* ApplicationTP3.exo3("textes", "rendre"); */

       WebSearchEngine webSearchEngine = new WebSearchEngine("textes", 8282);
       
    }
}

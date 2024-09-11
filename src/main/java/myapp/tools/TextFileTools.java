package myapp.tools;

import java.io.IOException;
import java.nio.file.*;

public class TextFileTools {
    
    public static String readWord(FileReader fileReader) {
        if (fileReader.isEndOfFile()) return null;

        var currentChar = fileReader.readChar();
        var currentWord = "";

        while ((currentChar != ' ') 
            && (currentChar != '.')
            && (currentChar != ',')
            && (currentChar != ':')
            && (currentChar != ';')
            && (currentChar != '\n')
            && (currentChar != '\r')
             ) {
                currentWord = currentWord + currentChar;
                if (fileReader.isEndOfFile()) break;
                currentChar = fileReader.readChar();
            }


        return currentWord;
    }

    public static int countWord(FileReader fileReader, String searchWord) {
        if (fileReader.isEndOfFile()) return 0;
        var Word = "";
        var count = 0;

        while ((Word = readWord(fileReader)) != null) {
            if (Word.equals(searchWord)) {
                count++;
            }
        }

        return count;

    }

    public static void walkDirectory(String directory, ProcessFile processFile) throws IOException {
        Files.walk(Paths.get(directory))
            .filter(Files::isRegularFile)
            .sorted()
            .forEach(processFile::process);
    }

}

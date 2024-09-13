package myapp.tools;

import java.io.IOException;
import java.nio.file.*;

import myapp.collections.List;

public class TextFileTools {
    
    public static String readWord(FileReader fileReader) {
        if (fileReader.isEndOfFile()) return null;

        var currentChar = fileReader.readChar();
        String currentWord = "";

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

    public static List<String> readFile(FileReader fileReader) {
        List<String> list = new List<String>();
        String word;

        while ((word = TextFileTools.readWord(fileReader)) != null) {
            if (word != "") list.add(word);
        }

        return list;
    }

    public static List<List<String>> readDirectory(String directory) throws IOException {
        List<List<String>> list = new List<List<String>>();

        walkDirectory(directory, (file) -> {
            FileReader fileReader = new FileReader(file.toString());
            List<String> words = TextFileTools.readFile(fileReader);
            list.add(words);
        });

        return list;
    }

}
package myapp.app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.charset.Charset;

import myapp.tools.FileReader;
import myapp.tools.ProcessFile;
import myapp.tools.TextFileTools;

public class ApplicationTP1 {

    public static class Occurrences {
        private char character;
        private int count;

        public Occurrences(char character) {
            this.character = character;
        }

        public Occurrences(char character, int count) {
            this.character = character;
            this.count = count;
        }

    
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + character;
            result = prime * result + count;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Occurrences other = (Occurrences) obj;
            if (character != other.character)
                return false;
            if (count != other.count)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Occurrences [character=" + character + ", count=" + count + "]";
        }


        public void incrementCount() {
            count++;
        }

        public char getCharacter() {
            return character;
        }


        public int getCount() {
            return count;
        }
    }
    public static class DisplayFileName implements ProcessFile {

        @Override
        public void process(Path filename) {
            System.out.println(filename.getFileName());
        }

    }

    public Character readChar(FileReader fileReader) {
        if(fileReader.isEndOfFile()) return null;
        return fileReader.readChar();
    }

    public int countAl(FileReader fileReader) {
        
        var count = 0;
        var charIsA = false;

        while (!fileReader.isEndOfFile()) {
            var currentChar = fileReader.readChar();
            if (charIsA && currentChar == 'l') count++;
            charIsA = (currentChar == 'a');
        }


        return count;
    }

    public int countWords(FileReader fileReader) {
        var count = 0;
        var isCharacter = false;

        while (!fileReader.isEndOfFile()) {
            var currentChar = fileReader.readChar();

            if ((currentChar != ' ') 
            && (currentChar != '.')
            && (currentChar != ',')
            && (currentChar != ':')
            && (currentChar != ';')
            && (currentChar != '\n')
            ) {
                isCharacter = true;
            } else {
                if (isCharacter) {
                    count++;
                }
                isCharacter = false;
            }
        }

        if (isCharacter) {
            count++;
        }

        return count;
    }

    public String[] read5Words(FileReader fileReader) {
        var Words = new String[5];
        var wordCount = 0;

        while (wordCount < 5 && !fileReader.isEndOfFile()) {
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

            if (!currentWord.isEmpty()) {
                Words[wordCount] = currentWord;
                wordCount++;
            }

            
        }

        return Words;
    } 

    public Occurrences[] countPunctuations(FileReader fileReader) {
        Occurrences[] occurrences = {
            new Occurrences('.'),
            new Occurrences(','),
            new Occurrences(':'),
            new Occurrences(';')
        };

        while (!fileReader.isEndOfFile()) {
            var currentChar = fileReader.readChar();

            for (Occurrences occurrence : occurrences) {
                if (currentChar == occurrence.getCharacter()) {
                    occurrence.incrementCount();
                }
            }
        }

        return occurrences;
    }

    /* Classe Interne
    public void displayDirectoryContent(String directory) throws IOException {
        TextFileTools.walkDirectory(directory, new DisplayFileName());
    } */
   
    /* Classe Anonyme
    public void displayDirectoryContent(String directory) throws IOException {
        TextFileTools.walkDirectory(directory, new ProcessFile() {

            @Override
            public void process(Path path) {
                System.out.println(path.getFileName());
            }
            
        });
    } */

    public void displayDirectoryContent(String directory) throws IOException {
        TextFileTools.walkDirectory(directory,(path) -> System.out.println(path.getFileName()));
    }

    public void displayFilesWithWord(String directory, String word) throws IOException {
        TextFileTools.walkDirectory(directory, name -> {
            FileReader file = new FileReader(name.toString(), Charset.forName("Windows-1252"));
            if (TextFileTools.countWord(file, word) > 0) {
                System.out.println(name);
            }
        });
    }


}

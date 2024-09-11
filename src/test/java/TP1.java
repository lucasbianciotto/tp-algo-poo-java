import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import myapp.app.ApplicationTP1;
import myapp.tools.FileReader;
import myapp.tools.ProcessFile;
import myapp.tools.TextFileTools;

public class TP1 {

    private final ApplicationTP1 application = new ApplicationTP1();
    
    @Test
    void exo2() {
        FileReader reader = new FileReader("BellumCivile.txt");
        Assertions.assertEquals('a', application.readChar(reader));
        reader = FileReader.fromString("Hello");
        Assertions.assertEquals('H', application.readChar(reader));
        reader = FileReader.fromString("");
        Assertions.assertNull(application.readChar(reader));
    }

    @Test
    public void exo3() {
        FileReader reader = FileReader.fromString("a la pause al bundy a une lampe et il est allité");
        Assertions.assertEquals(2, application.countAl(reader));
        reader = FileReader.fromString("il a la faim");
        Assertions.assertEquals(0, application.countAl(reader));
        reader = FileReader.fromString("");
        Assertions.assertEquals(0, application.countAl(reader));
        reader = FileReader.fromString("a");
        Assertions.assertEquals(0, application.countAl(reader));
    }

    @Test
    public void exo4() {
        FileReader reader = FileReader.fromString("un mot... mais: un autre mot, et encore;. un autre\nsur une nouvelle ligne.");
        Assertions.assertEquals(14, application.countWords(reader));
        reader = FileReader.fromString("One");
        Assertions.assertEquals(1, application.countWords(reader));
        reader = FileReader.fromString("One.");
        Assertions.assertEquals(1, application.countWords(reader));
        reader = FileReader.fromString("One. Two");
        Assertions.assertEquals(2, application.countWords(reader));
        reader = FileReader.fromString("a");
        Assertions.assertEquals(1, application.countWords(reader));
        reader = FileReader.fromString("");
        Assertions.assertEquals(0, application.countWords(reader));
        reader = FileReader.fromString(".");
        Assertions.assertEquals(0, application.countWords(reader));
        reader = FileReader.fromString("..");
        Assertions.assertEquals(0, application.countWords(reader));
        reader = new FileReader("BellumCivile.txt");
        Assertions.assertEquals(342, application.countWords(reader));
    }

    @Test
    public void exo5() {
        FileReader reader = FileReader.fromString("One");
        Assertions.assertArrayEquals(new String[] { "One", null, null, null, null },
        application.read5Words(reader));
        reader = FileReader.fromString("One. Two");
        Assertions.assertArrayEquals(new String[] { "One", "Two", null, null, null },
        application.read5Words(reader));
        reader = FileReader.fromString("One.\n Two");
        Assertions.assertArrayEquals(new String[] { "One", "Two", null, null, null },
        application.read5Words(reader));
        reader = FileReader.fromString("One.\r Two");
        Assertions.assertArrayEquals(new String[] { "One", "Two", null, null, null },
        application.read5Words(reader));
        reader = FileReader.fromString(".");
        Assertions.assertArrayEquals(new String[] { null, null, null, null, null },
        application.read5Words(reader));
        reader = FileReader.fromString("");
        Assertions.assertArrayEquals(new String[] { null, null, null, null, null },
        application.read5Words(reader));
        reader = new FileReader("BellumCivile.txt");
        Assertions.assertArrayEquals(new String[] { "ad", "ea", "Caesar", "respondit", "nulli"},
        application.read5Words(reader));
    }

    @Test
    public void exo6() {
        FileReader reader = FileReader.fromString("One");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 0), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString("");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 0), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString("One.");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 1), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString("One. Two");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 1), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString(".");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 1), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString("..");
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 2), application.countPunctuations(reader)[0]);
        reader = FileReader.fromString("un mot... mais: un autre mot, et encore;. un autre\nsur une nouvelle ligne.");
        ApplicationTP1.Occurrences[] occurrences = application.countPunctuations(reader);
        Assertions.assertEquals(new ApplicationTP1.Occurrences('.', 5), occurrences[0]);
        Assertions.assertEquals(new ApplicationTP1.Occurrences(',', 1), occurrences[1]);
        Assertions.assertEquals(new ApplicationTP1.Occurrences(':', 1), occurrences[2]);
        Assertions.assertEquals(new ApplicationTP1.Occurrences(';', 1), occurrences[3]);
    }

    @Test
    public void exo7() {
        Assertions.assertEquals(0, TextFileTools.countWord(FileReader.fromString(""),""));
        Assertions.assertEquals(0, TextFileTools.countWord(FileReader.fromString(""),"one"));
        Assertions.assertEquals(0, TextFileTools.countWord(FileReader.fromString("ONe"),"one"));
        Assertions.assertEquals(1, TextFileTools.countWord(FileReader.fromString("ONe"),"ONe"));
        Assertions.assertEquals(2, TextFileTools.countWord(FileReader.fromString("ONe two one Two ONe"),"ONe"));
    }

    @Test
    public void exo8() throws IOException {
        final boolean[] first = {false, false};
        TextFileTools.walkDirectory("textes", new ProcessFile() {
            @Override
            public void process(Path fileName) {
                if (!first[0]) {
                    Assertions.assertEquals("textes/code_civil/1.txt", fileName.toString());
                    first[0] = true;
                } else if (!first[1]) {
                    Assertions.assertEquals("textes/code_civil/10.txt", fileName.toString());
                    first[1] = true;
                }
            }
        });
    }


}

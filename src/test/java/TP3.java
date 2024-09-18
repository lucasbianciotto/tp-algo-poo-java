import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import myapp.searchengine.Index;
import myapp.searchengine.Tree;

public class TP3 {
    
    @Test
    public void exo1() {
        Tree tree = new Tree();
        tree.insert("Hello", "f1");
        tree.insert("Hello", "f2");
        tree.insert("World", "f3");
        tree.insert("!", "f4");
        Assertions.assertEquals("Hello", tree.getHead().getWord());
        Assertions.assertEquals("World", tree.getHead().getRightChild().getWord());
        Assertions.assertEquals("!", tree.getHead().getLeftChild().getWord());
    }

    @Test
    public void exo2() throws IOException {
        Index index = new Index();
        index.build("textes");
        index.walk(node -> {
            Assertions.assertNotNull(node);
        });
    }

    @Test
    public void exo3() throws IOException {
        Index index = new Index();
        index.build("textes");
        var result = index.find("rendre");
        if (result != null) {
            Assertions.assertEquals("rendre", result.getWord());
        } else {
            Assertions.fail();
        }
        
    }
}

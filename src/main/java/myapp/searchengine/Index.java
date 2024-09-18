package myapp.searchengine;

import java.io.IOException;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.tools.FileReader;
import myapp.tools.TextFileTools;

import java.util.function.Consumer;

public class Index {
    Tree tree = new Tree();

    
    public void build(String path) throws IOException {
        TextFileTools.walkDirectory(path, (file) -> {
            FileReader fileReader = new FileReader(file.toString());
            Iterator<String> wordsList = new Iterator<>(TextFileTools.readFile(fileReader));
            while (wordsList.hasNext()) {
                try {
                    tree.insert(wordsList.next(), file.toString());
                } catch (EndOfListException e) {
                    break;
                }
            }
        });
    }

    public TreeNode find(String word) {
        if (word == null) return null;
        
        final TreeNode[] result = new TreeNode[1];
        result[0] = null;

        this.walk(node -> {
            if (node.getWord().compareToIgnoreCase(word) == 0){
                result[0] = node;
            }
        });
        return result[0] != null ? result[0] : null;
    }

    public void walk(Consumer<TreeNode> process) {
        tree.walk(process);
    }
}

package myapp.searchengine;

import java.util.function.Consumer;

import myapp.collections.List;

public class Tree {
    private TreeNode head = null;

    public void insert(String word, String fileName) {
        head = insert(head, word, fileName);
    }

    private TreeNode insert(TreeNode node, String word, String fileName) {
        WordWeigth wordWeigth = new WordWeigth(fileName);
        if (node == null) {
            TreeNode newNode = new TreeNode(word);
            newNode.getFilesList().add(wordWeigth);
            return newNode;
        }
        String currentWord = node.getWord();
        int result = word.compareToIgnoreCase(currentWord);
        if (result == 0) {
            WordWeigth currentWordWeigth = node.getFilesList().findFirst(wordWeigth);
            if (currentWordWeigth == null) {
                node.getFilesList().add(wordWeigth);
            } else {
                currentWordWeigth.setWeight(currentWordWeigth.getWeight()+1);
            }
            node.getFilesList().sort(WordWeigth::compareTo);
        } else if (result < 0) {
            node.setLeftChild(insert(node.getLeftChild(), word, fileName));
        } else {
            node.setRightChild(insert(node.getRightChild(), word, fileName));
        }
        return node;
    }

    public void walk(Consumer<TreeNode> process) {
        walk(head, process);
    }

    private void walk(TreeNode node, Consumer<TreeNode> process) {
        if (node == null) return;
        walk(node.getLeftChild(), process);
        process.accept(node);
        walk(node.getRightChild(), process);
    }

    public TreeNode getHead() {
        return head;
    }

    

    
}

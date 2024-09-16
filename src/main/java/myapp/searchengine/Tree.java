package myapp.searchengine;

import java.util.function.Consumer;

public class Tree {
    private TreeNode head = null;

    public void insert(String word, String fileName) {
        head = insert(head, word, fileName);
    }

    private TreeNode insert(TreeNode node, String word, String fileName) {
        if (node == null) {
            node = new TreeNode(word);
            node.getFilesList().add(fileName);
            return node;
        } 
        var currentWord = node.getWord();
        var compareWord = word.compareToIgnoreCase(currentWord);
        if (compareWord == 0) {
            var hasFile = node.getFilesList().contains(fileName);
            if (!hasFile) node.getFilesList().add(fileName);
        } else if (compareWord < 1) {
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

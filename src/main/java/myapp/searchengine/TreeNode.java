package myapp.searchengine;

import myapp.collections.List;

public class TreeNode {
    private final String word;
    private TreeNode leftChild = null;
    private TreeNode rightChild = null;
    private List<String> filesList = new List<>();

    public TreeNode(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public List<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<String> filesList) {
        this.filesList = filesList;
    }

    

    



    

}

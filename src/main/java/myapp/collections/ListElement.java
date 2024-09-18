package myapp.collections;

class ListElement<T> {
    private T data;
    private ListElement<T> nextElement = null;
    private ListElement<T> previousElement = null;

    public ListElement(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ListElement<T> getNextElement() {
        return nextElement;
    }

    public void setNextElement(ListElement<T> nextElement) {
        this.nextElement = nextElement;
    }

    public ListElement<T> getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(ListElement<T> previousElement) {
        this.previousElement = previousElement;
    }

    

    

}


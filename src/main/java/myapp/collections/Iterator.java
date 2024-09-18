package myapp.collections;

public class Iterator<T> {
    private final List<T> list;
    private ListElement<T> current = null;

    public Iterator(List<T> list) {
        this.list = list;
        restart();
    }

    public void restart() {
        current = list.head;
    }

    public T next() throws EndOfListException {
        if (current == null) throw new EndOfListException();
        T data = current.getData();
        current = current.getNextElement();
        return data;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T previous() throws EndOfListException {
        if (current == null) throw new EndOfListException();
        T data = current.getData();
        current = current.getPreviousElement();
        return data;
        
    }

}
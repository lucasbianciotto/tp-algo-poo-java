package myapp.collections;

public class List<T> {
    ListElement<T> head = null;

    public void addFirst(T data) {
        ListElement<T> newElement = new ListElement<T>(data);
        if (head == null) {
            head = newElement;
        } else {
            ListElement<T> nextElement = head;
            head = newElement;
            newElement.setNextElement(nextElement);
        }
    }

    public void add(T data) {
        ListElement<T> newElement = new ListElement<T>(data);
        if (head == null) {
            head = newElement;
        } else {
            ListElement<T> loop = head;
            while (loop.getNextElement() != null) {
                loop = loop.getNextElement();
            }

            loop.setNextElement(newElement);
        }
    }

    public boolean contains(T data) {
        ListElement<T> loopElement = head;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) return true;
            loopElement = loopElement.getNextElement();
        }
        return false;
    }

    public T findFirst(T data) {
        if (head == null) return null;
        ListElement<T> searchElement = new ListElement<T>(data);

        ListElement<T> loop = head;
        while (loop != null && !loop.equals(searchElement)) {
            loop = loop.getNextElement();
        }
        return loop != null ? loop.getData() : null;
    }

    public void delete(Stockable data) {
        ListElement<T> loop = head;
        ListElement<T> previousElement = null;
        while (loop != null) {
            if (loop.getData().equals(data)) {
                if (previousElement == null) { 
                    head = head.getNextElement();
                } else {
                    previousElement.setNextElement(loop.getNextElement());
                }
                return;
            }
            previousElement = loop;
            loop = loop.getNextElement();
        }
    }

}


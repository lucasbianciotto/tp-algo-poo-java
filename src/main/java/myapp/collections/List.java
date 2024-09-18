package myapp.collections;

import java.util.Comparator;

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
            nextElement.setPreviousElement(newElement);
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
            newElement.setPreviousElement(loop);
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
                    head.setPreviousElement(null);
                } else {
                    previousElement.setNextElement(loop.getNextElement());
                    loop.getNextElement().setPreviousElement(previousElement);
                }
                return;
            }
            previousElement = loop;
            loop = loop.getNextElement();
        }
    }

    public void sort(Comparator<? super T> comparator) {
        ListElement<T> loopElement = head;
        while (loopElement.getNextElement() != null) {
            ListElement<T> minElement = loopElement;
            ListElement<T> innerLoopElement = loopElement.getNextElement();
            while (innerLoopElement != null) {
                if (comparator.compare(innerLoopElement.getData(), minElement.getData()) < 0) {
                    minElement = innerLoopElement;
                }
                innerLoopElement = innerLoopElement.getNextElement();
            }
            if (minElement != loopElement) {
                ListElement<T> prevCurrent = loopElement.getPreviousElement();
                ListElement<T> nextMinElement = minElement.getNextElement();
                ListElement<T> prevMinElement = minElement.getPreviousElement();
                minElement.setNextElement(loopElement);
                minElement.setPreviousElement(prevCurrent);
                loopElement.setPreviousElement(minElement);
                prevMinElement.setNextElement(nextMinElement);
                if (nextMinElement != null) {
                    nextMinElement.setPreviousElement(prevMinElement);
                }
                if (prevCurrent == null) {
                    head = minElement;
                } else {
                    prevCurrent.setNextElement(minElement);
                    loopElement.setPreviousElement(minElement);
                }

            } else {
                loopElement = loopElement.getNextElement();
            }
        }
    }

}


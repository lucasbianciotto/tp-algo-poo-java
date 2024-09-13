package myapp.collections;

public class ListOfStockable {
    ListElementOfStockable head = null;

    public void addFirst(Stockable data) {
        ListElementOfStockable newElement = new ListElementOfStockable(data);
        if (head == null) {
            head = newElement;
        } else {
            ListElementOfStockable nextElement = head;
            head = newElement;
            newElement.setNextElement(nextElement);
        }
    }

    public Stockable[] toArray() {
        // count number of elements
        int count = 0;
        ListElementOfStockable loopElement = head;
        while (loopElement != null) {
            count = count + 1;
            loopElement = loopElement.getNextElement();
        }
        // create array from list content
        Stockable[] array = new Stockable[count];
        int index = 0;
        loopElement = head;
        while (loopElement != null) {
            array[index] = loopElement.getData();
            index = index + 1;
            loopElement = loopElement.getNextElement();
        }
        return array;
    }

    public void add(Stockable data) {
        ListElementOfStockable newElement = new ListElementOfStockable(data);
        if (head == null) {
            head = newElement;
        } else {
            ListElementOfStockable loop = head;
            while (loop.getNextElement() != null) {
                loop = loop.getNextElement();
            }

            loop.setNextElement(newElement);
        }
    }

    public boolean contains(Stockable data) {
        ListElementOfStockable loopElement = head;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) return true;
            loopElement = loopElement.getNextElement();
        }
        return false;
    }

    public Stockable findFirst(Stockable data) {
        if (head == null) return null;
        ListElementOfStockable searchElement = new ListElementOfStockable(data);

        ListElementOfStockable loop = head;
        while (loop != null && !loop.equals(searchElement)) {
            loop = loop.getNextElement();
        }
        return loop != null ? loop.getData() : null;
    }

    public void delete(Stockable data) {
        ListElementOfStockable loop = head;
        ListElementOfStockable previousElement = null;
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

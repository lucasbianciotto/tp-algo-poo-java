package myapp.collections;

public class IteratorOfStockable {
    private ListOfStockable listOfStockable;
    private ListElementOfStockable firstElement;

    public IteratorOfStockable(ListOfStockable listOfStockable) {
        this.listOfStockable = listOfStockable;

        restart();

    }

    public void restart() {
        this.firstElement = this.listOfStockable.head;
    }

    public Stockable next() throws EndOfListException {
        var nextElement = this.firstElement.getNextElement();
        if (nextElement == null) throw new EndOfListException();
        this.firstElement = nextElement;
        return firstElement.getData(); 
    }

    public boolean hasNext() {
        var nextElement = this.firstElement.getNextElement();
        return nextElement != null ? true : false;
    }

}

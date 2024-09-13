package myapp.collections;

class ListElementOfStockable {
    private Stockable data;
    private ListElementOfStockable nextElement = null;

    public ListElementOfStockable(Stockable data) {
        this.data = data;
    }

    public Stockable getData() {
        return data;
    }

    public ListElementOfStockable getNextElement() {
        return nextElement;
    }

    public void setNextElement(ListElementOfStockable nextElement) {
        this.nextElement = nextElement;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((nextElement == null) ? 0 : nextElement.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListElementOfStockable other = (ListElementOfStockable) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (nextElement == null) {
            if (other.nextElement != null)
                return false;
        } else if (!nextElement.equals(other.nextElement))
            return false;
        return true;
    }

    

}

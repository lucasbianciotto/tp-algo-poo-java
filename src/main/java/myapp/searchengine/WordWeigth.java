package myapp.searchengine;

public class WordWeigth implements Comparable<WordWeigth> {
    private final String filename;
    private int weight = 1;

    

    public WordWeigth(String fileName) {
        this.filename = fileName;
    }

    public String getFilename() {
        return filename;
    }
    
    public int getWeight() {
        return weight;
    }



    public void setWeight(int weight) {
        this.weight = weight;
    }



    @Override
    public String toString() {
        return filename + " (" + weight + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((filename == null) ? 0 : filename.hashCode());
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
        WordWeigth other = (WordWeigth) obj;
        if (filename == null) {
            if (other.filename != null)
                return false;
        } else if (!filename.equals(other.filename))
            return false;
        return true;
    }

    @Override
    public int compareTo(WordWeigth o) {
        return Integer.compare(o.weight, this.weight);
    }
}

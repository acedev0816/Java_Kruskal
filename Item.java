package union;

public class Item<T> implements Comparable<Item<T>> {
    private T elem;
    private Item<T> parent = this;
    private Integer rank = 0;

    public Item(T elem) {
        this.elem = elem;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public Item<T> getParent() {
        return parent;
    }

    public void setParent(Item<T> parent) {
        this.parent = parent;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void increaseRank() {
        rank++;
    }

    @Override
    public int compareTo(Item<T> it ) {
        return rank.compareTo(it.getRank());
    }
}

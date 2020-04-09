package union;
import java.util.*;

public class UnionFind<T> {
    private final HashMap<T, Item<T>> set;       

    public UnionFind() {
        set = new HashMap<>();
    }	

    public Item<T> find(T elem) {
        Item<T> it = find(retrieveItem(elem));

        if (it == it.getParent()) {
            return it;
        }

        it.setParent(find(it.getParent()));
        return it.getParent();
    }

    private Item<T> find(Item<T> item) {
        if (item == item.getParent()) {
            return item;
        }
        return find(item.getParent().getElem());
    }

    public void union(T elem1, T elem2) {
        Item<T> e1rep = find(retrieveItem(elem1));
        Item<T> e2rep = find(retrieveItem(elem2));

        if (e1rep == e2rep) {
           return;
        }

        int comparison = e1rep.compareTo(e2rep);

        if ( comparison < 0 ) {
            e1rep.setParent(e2rep);
        } else if ( comparison > 0 ) {
            e2rep.setParent(e1rep);
        } else {
            e2rep.setParent(e1rep);
            e1rep.increaseRank();
        }
    }

    private Item<T> retrieveItem(T elem) {
        Item<T> it = set.get(elem);
        return it;
    }

    public void makeSet(T elem) {
        if (!set.containsKey(elem)) {
            Item<T> item= new Item<>(elem);
            set.put(elem, item);
        }
    }

    public boolean contains(T elem) {
        return set.containsKey(elem);
    }
}

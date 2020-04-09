package union;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class UnionFindUnitTests {

    private UnionFind<Integer> testSet;
    private Item<Integer> i1,i2,i3,i4,i5;

    @Before
    public void createMap() {
        i1 = new Item<>(1);
        i2 = new Item<>(2);    
        i3 = new Item<>(3);
        i4 = new Item<>(4);
        i5 = new Item<>(5);   
        testSet = new UnionFind<>();
    }
	
    @Test
    public void makeSetTest() {
        testSet.makeSet(i1.getElem());
        assertTrue(testSet.contains(i1.getElem()));
        testSet.makeSet(i2.getElem());
        assertTrue(testSet.contains(i2.getElem()));
        testSet.makeSet(i3.getElem());
        assertTrue(testSet.contains(i3.getElem()));
        testSet.makeSet(i4.getElem());
        assertTrue(testSet.contains(i4.getElem()));
        testSet.makeSet(i5.getElem());
        assertTrue(testSet.contains(i5.getElem()));
    }

    @Test
    public void findTest() {
        assertEquals(i1.getElem(), testSet.find(1).getElem());
        testSet.makeSet(i2.getElem());
        assertEquals(i2.getElem(), testSet.find(2).getElem());
    }

    @Test
    public void unionTest() {
    }

}

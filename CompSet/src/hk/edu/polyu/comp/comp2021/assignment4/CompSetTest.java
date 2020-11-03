package hk.edu.polyu.comp.comp2021.assignment4;

import org.junit.Test;

import java.util.List;

public class CompSetTest {
    @Test
    public void testIntegerCompSet(){
        CompSet<Integer> integerSet = new CompSet<>();
        assert integerSet.isEmpty();
        assert integerSet.getCount() == 0;
        integerSet.add(1);
        assert !integerSet.isEmpty();
        integerSet.add(2);
        integerSet.add(3);
        assert integerSet.getCount() == 3;
        assert integerSet.contains(3);
        assert !integerSet.contains(4);
        integerSet.remove(3);
        integerSet.remove(4);
        assert !integerSet.contains(4);
    }

    @Test
    public void testStringCompSet(){
        CompSet<String> stringSet = new CompSet<>();
        stringSet.add("a");
        stringSet.add("b");
        stringSet.add("a");
        stringSet.add("c");
        stringSet.add("d");
        List<String> uniqueStrings = stringSet.getElements();
        CompSet<String> stringSet2 = new CompSet<>(uniqueStrings);
        assert stringSet.equals(stringSet2);
        stringSet2.remove("c");
        assert !stringSet.equals(stringSet2);
    }
}

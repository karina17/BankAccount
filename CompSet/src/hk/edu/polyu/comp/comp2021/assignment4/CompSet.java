package hk.edu.polyu.comp.comp2021.assignment4;

import java.util.ArrayList;
import java.util.List;

/** A set based on the implementation as described on page 7 of Lecture07.pdf. */
class CompSet<T> {

	/* Number of buckets to accommodate all elements. */
    private static final int NUMBER_OF_BUCKETS = 1023;
    private List<T>[] storage;
    
    private int getIndex(T element) {
        return element.hashCode() % NUMBER_OF_BUCKETS;
    }

	/** Instantiate a new CompSet object. */
    public CompSet() {
    	storage = new ArrayList[NUMBER_OF_BUCKETS];
    	for (int i = 0; i < this.storage.length; i++) {
    		this.storage[i] = new ArrayList<>();
    	}
    }
    
	/** Instantiate a new CompSet object with 'elements'. */
    public CompSet(List<T> elements) {
    	storage = new ArrayList[NUMBER_OF_BUCKETS];
    	for (int i = 0; i < this.storage.length; i++) {
    		this.storage[i] = new ArrayList<>();
    	}
    	
    	for (T element : elements) {
    		this.add(element);
    	}
    }

	/** Number of elements in the set. */
    public int getCount() {
    	int count = 0;
		for (int i = 0; i < storage.length; i++) {
			if (!storage[i].isEmpty()) {
				count++;
			}
		}
		return count;
    }

	/** Is the set empty? */
    public boolean isEmpty() {
    	if (this.getCount() == 0)
    		return true;
    	else return false;
    }

	/** Is 'element' contained in this set? */
    public boolean contains(T element) {
    	 if (this.storage[getIndex(element)].contains(element))
    		 return true;
    	 else return false;
    }

	/** List of unique elements from this set. */
    public List<T> getElements() {
    	List<T> temp = new ArrayList();
        for (int i = 0; i < NUMBER_OF_BUCKETS; i++){
  	         temp.addAll(storage[i]);
  	    }
        return temp;
    }

	/** Add 'element' to this set. */
    public void add(T element) {
    	if (!contains(element))
    		storage[getIndex(element)].add(element);
    }

	/** Is 'other' equal to this set? */
    public boolean equals(Object other){
    	if (other instanceof CompSet)
    		return (this.getElements().equals(((CompSet) other).getElements()));
		return false;
    }
    

	/** Remove 'element' from this set. */
    public void remove (T element) {
    	if(contains(element))
    		storage[element.hashCode() % storage.length].remove(element);
    	else System.out.println("No such element found");
    }
}


package mapClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import treeClasses.LinkedBST;
import interfaces.Entry;
import interfaces.Map;
import positionalStructures.Position;

public class BSTMap<K, V> implements Map<K, V> {
	// the binary search tree supporting this implementation of the map
	private LinkedBST<K, V> tree;   	
	
	/**
	 * Creates an instance of BSTMap. 
	 * @param cmp the comparator of keys that is received and which shall
	 * be used to compare keys of entries. 
	 */
	public BSTMap(Comparator<K> cmp) { 
		tree = new LinkedBST<>(cmp); 
	}
	
	@Override
	/**
	 * the size of the map is the size of the tree. 
	 */
	public int size() {
		return tree.size();
	}

	@Override
	/** 
	 * the map is empty iff the tree is empry
	 */
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	/**
	 * Get operation of the map. 
	 */
	public V get(K key) {
		// for for the entry having given key in the tree, if any
		Entry<K, V> result = tree.get(key); 
		
		// if not found, return null
		if (result == null) return null; 
		
		// if found, the value of the element that matches the search.
		return result.getValue();
	}

	@Override
	/**
	 * put of the map...
	 */
	public V put(K key, V value) {
		ModifiableEntry<K, V> result = (ModifiableEntry<K, V>) tree.get(key); 
		
		// ADD CODE HERE AS PART OF EXERCISE 1.
		
		if(tree.size() == 0) {
			tree.add(key, value);
			return null;
		}
		
		Iterator<Position<Entry<K, V>>> iter = tree.positions().iterator();
		Position<Entry<K, V>> pos ;
		
		while(iter.hasNext()) {
			pos = iter.next();
			if(pos.getElement().getKey().equals(key)) {
				V v = pos.getElement().getValue();
				result.setValue(value);
				pos.setElement(result);
				return v;
			}
				
		}
		
		 tree.add(key, value);
		 
		return null;
	}

	@Override
	/**
	 * remove of the map
	 */
	public V remove(K key) {
		
		// SUBSTITUE THIS STATEMENT WITH YOUR CODE NEEDED FOR THIS OPERATION
        // AS PART OF EXERCISE 1.
		
		
		Iterator<Position<Entry<K, V>>> iter = tree.positions().iterator();
		Position<Entry<K, V>> pos;
		
		while (iter.hasNext()) {
			
			pos = iter.next();
			
			if (pos.getElement().getKey().equals(key)) {
				V v = pos.getElement().getValue();
				tree.remove(pos.getElement().getKey());
				return v;
				
			}
		}
		return null;  
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> keys = new ArrayList<>();
		// ADD CODE HERE AS PART OF EXERCISE 2.
		for (Entry<K, V> e : tree) { keys.add(e.getKey()); }
		return keys; 
	}

	@Override
	public Iterable<V> values() {
		// ADD CODE HERE AS PART OF EXERCISE 2. 
		// USE THE FACT THE THE LINKED BINARY TREE IS ITERABLE of its elements!
		ArrayList<V> val = new ArrayList<>();

			for (Entry<K, V> e : tree) { val.add(e.getValue()); }
			
		return val;
		  // NEED TO CHANGE THIS STATEMENT TOO
		
	}

	@Override

	public Iterable<Entry<K, V>> entrySet() {
    ArrayList<Entry<K, V>> eList = new ArrayList<>(); 
        
    for (Position<Entry<K, V>> p : tree.positions())    // positions in inorder for binary tree
         eList.add(p.getElement()); 
        
    return eList;
}


	public void displayMAPTree() {   // This operation has been added just for testing
		this.tree.display();
	}
		
}

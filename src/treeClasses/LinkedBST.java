package treeClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import interfaces.BinarySearchTree;
import interfaces.Entry;
import mapClasses.ModifiableEntry;
import positionalStructures.Position;

/**
 * Implementation of the Binary Search Tree as a linked structure. The 
 * approach is to implement it as a subclass of LinkedBinaryTree. That
 * way, we will be able to reuse code from the LinkedBinaryTree, and 
 * from the AbstractTree classes. 
 * 
 * Here, each node holds an element of type Entry<K, V>. Data type K represents 
 * a valid object data type satisfying an order relation compatible with
 * the comparator that is received by any created instance of the BST. 
 * 
 * 
 * @author pedroirivera-vega
 *
 * @param <K> Data type of the keys.
 * @param <V> Data type of the values.
 */
public class LinkedBST<K, V> extends LinkedBinaryTree<Entry<K, V>>
implements BinarySearchTree<K, V>  {
	// The comparator that defines the order relation satisfied by objects of type E
	private Comparator<K> cmp; 

	/**
	 * The constructor.
	 * @param cmp the comparator
	 */
	public LinkedBST(Comparator<K> cmp) { 
		super();         // invoke default constructor of LinkedBinaryTree
		// to initialize inherited components

		this.cmp = cmp;  // the comparator
	}

	/**
	 * Adds a new entry pair with the given key and value 
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void add(K key, V value) { 
		Entry<K, V> e = new BSTEntry<>(key, value); 
		if (isEmpty()) addRoot(e);    // in this case, the new position will become the root,  
		// and the only node of the tree		           
		else recInsert(root(), e);      // in this case, traverse the tree in search of the right
		// location where to put the new element’s position
	}


	/**
	 * Internal private method. 
	 * Recursively look for the location where the new position with element e is to be inserted. 
	 * That new position will be inserted as a new leaf of the binary tree. 
	 * @param r root of the subtree where the insertion will take place.
	 * 
	 * @param e the new element (an entry) to be added to the tree in a new position
	 */
	private void recInsert(Position<Entry<K, V>> r, Entry<K, V> e) {
		int c = cmp.compare(e.getKey(), r.getElement().getKey()); 
		if (c < 0) 
			if (!hasLeft(r))
				super.addLeft(r, e); 
			else 
				recInsert(left(r), e); 
		else if (c > 0)
			if (!hasRight(r))
				super.addRight(r, e); 
			else 
				recInsert(right(r), e); 
		else   // c==0 randomly pick one of the subtrees and insert there
		{
			double rnd = Math.random(); 
			if (rnd < .5) 
				if (!hasLeft(r))
					super.addLeft(r, e); 
				else 
					recInsert(left(r), e); 
			else 
				if (!hasRight(r))
					super.addRight(r, e); 
				else 
					recInsert(right(r), e); 

		}

	}

	/**
	 * Internal private method. 
	 * Search for a position whose element matches the element being searched for
	 * @param r the root of the subtree where the search is done
	 * @param key key to search for
	 * @return reference to a position in the tree containing a matching element. If none is 
	 * found, then it returns null. 
	 */
	private Position<Entry<K, V>> findPosition(Position<Entry<K, V>> r, K key) { 
		if (r == null) return null; 
		int c = cmp.compare(key, r.getElement().getKey()); 
		if (c == 0) return r; 
		if (c < 0) return findPosition(left(r), key); 
		return findPosition(right(r), key);
	}

	/**
	 * Removes element e from the tree, and also one position. The
	 * position being removed is not necessarily the same position 
	 * where e is located. But the only element to be removed is e
	 * and the tree will continue satisfying the binary search tree
	 * fundamental property. 
	 * 
	 * @param e the element to be removed. It is presumed that e 
	 * contains at least whatever internal data is necessary
	 * to successfully compare with existing objects. 
	 *  
	 * @return the whole version of e as contained in the tree
	 * if it is the case that a matching element is found, and
	 * successfully removed. If no element is found, it returns null. 
	 */
	public Entry<K, V> remove(K key) { 
		Position<Entry<K, V>> pos = findPosition(root(), key); 


		if (pos == null) return null; 

		// Element To Return, the actual element in the tree that matches the searh criteria
		Entry<K, V> etr = pos.getElement(); 

		// Initiate the action to delete a position. The position finally deleted will be pos, only 
		// if pos has at most one child; otherwise, if pos has more than one child, the position to be 
		// finally removed will be the position containing the element that immediately follows element e
		// in order in the tree (the successor of e in the tree). That successor element will be moved to 
		// position pos, replacing the value there; which is the value to delete! ...
		deletePosition(pos); 

		return etr;     // returns a full copy of e as it is in the tree.
	}


	/**
	 * Internal private method. 
	 * Initiate the removal of a position. 
	 * @param pos
	 */
	private void deletePosition(Position<Entry<K, V>> pos) {
		if (left(pos) == null || right(pos) == null) { 
			// In this case, position pos has at most one child. Hence, pos itself
			// will be the position removed from the tree. For that, just apply the
			// remove operation that is inherited from LinkedBinaryTree. 
			super.remove(pos);       // inherited from LinedBinaryTree (see remove in that class)
		}
		else {
			// pos has two children. Find the position containing the element
			// in the tree that, in the order given, should go immediately after
			// the element in pos. In this case, that element is the minimum 
			// element in the right subtree of pos. 
			Position<Entry<K, V>> smallest = findSmallestPosition(right(pos));  

			// smallest is now the position in the tree that contains the
			// successor of pos.getElement(). Note that by property of the
			// binary search tree, position smallest has no left child; it
			// has at most only a right child. 

			// copy that successor element to position pos. 
			pos.setElement(smallest.getElement());

			// apply the inherited method from LinkedBinaryTree to remove position
			// smallest. Since smallest has at most only one child (the right child), 
			// the remove should work without any problem (See remove method in class
			// LinkedBinaryTree.
			super.remove(smallest);   // remove method that is inherited from LinkedBinaryTree
		}
	}


	/**
	 * Internal private method. 
	 * Find the smallest element in the binary search tree whose
	 * root is given. It is assumed the the tree is not empty.
	 * 
	 * @param r the root of the tree (or subtree) to explore. 
	 * PRE: r != null  
	 * 
	 * @return reference to the position in the tree with root
	 * r containing the smallest element in that subtree. Notice
	 * that to find such position we just need to move, beginning
	 * at r, all the way to the left until we find the first node  
	 * that does not have left child. That node will contain the
	 * smallest element in the binary search tree rooted a r. 
	 * This is so because of the binary search tree property. 
	 */
	private Position<Entry<K, V>> findSmallestPosition(Position<Entry<K, V>> r) {
		while (left(r) != null) 
			r = left(r); 
		return r;
	}
	
	// Forbid the direct use of the following operations, they can be invoked only in a
	// controlled manner from methods implemented in this subclass. 
	
	public Position<Entry<K, V>> addRight(Position<Entry<K, V>> p, Entry<K, V> e) { 
		throw new RuntimeException("Not valid operation on a Binary Search Tree"); 
	}
	
	public Position<Entry<K, V>> addLeft(Position<Entry<K, V>> p, Entry<K, V> e) { 
		throw new RuntimeException("Not valid operation on a Binary Search Tree"); 
	}
	
	public Entry<K, V> remove(Position<Entry<K, V>> p) { 
		throw new RuntimeException("Not valid operation on a Binary Search Tree"); 
	}
	
	public void Attach(Position<Entry<K, V>> p, LinkedBinaryTree<Entry<K, V>> t1, 
			LinkedBinaryTree<Entry<K, V>> t2) { 
		throw new RuntimeException("Not valid operation on a Binary Search Tree"); 
	}


	@Override
	public ArrayList<Entry<K, V>> getAll(K key) {
		ArrayList<Entry<K, V>> list = new ArrayList<>(); 
		fillListWithEntriesHavingKey(root(), key, list); 
		return list; 
	}

	private void fillListWithEntriesHavingKey(Position<Entry<K, V>> root, K key, 
			ArrayList<Entry<K, V>> list) {
		if (root == null) return; 
		int c = cmp.compare(key, root.getElement().getKey()); 
		if (c == 0) { 
			fillListWithEntriesHavingKey(left(root), key, list);
			list.add(root.getElement()); 
			fillListWithEntriesHavingKey(right(root), key, list); 
		} else if (c < 0)
			fillListWithEntriesHavingKey(left(root), key, list);
		else 
			fillListWithEntriesHavingKey(right(root), key, list);
		
	}

	@Override
	public Entry<K, V> get(K key) {
		Position<Entry<K, V>> ptr = findPosition(root(), key);
		if (ptr == null) return null; 
		return ptr.getElement();
	}

	
	private static class BSTEntry<K, V> implements ModifiableEntry<K, V> { 
		private K key; 
		private V value; 
		public BSTEntry(K key, V value) { 
			this.key = key; 
			this.value = value; 
		}
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public V setValue(V value) {
			V old = this.value; 
			this.value = value;
			return old; 
		}
		@Override
		public String toString() {
			return "[" + key + ", " + value + "]"; 
		}
		
		
	}

}
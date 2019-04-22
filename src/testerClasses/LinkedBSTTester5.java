package testerClasses;

import java.util.Comparator;

import interfaces.Entry;
import treeClasses.LinkedBST;

public class LinkedBSTTester5 {
	public static void main(String[] args) { 
		
		System.out.println("Tester 1 for LinkedBST.\n");
		
		LinkedBST<Integer, String> bst = 
			new LinkedBST<>(new NumberComparator1()); 
		bstInsert(1, "one 1", bst); 
		bstInsert(1, "one 2", bst); 
		bstInsert(1, "one 3", bst); 
		bstInsert(1, "one 4", bst); 
		bstInsert(1, "one 5", bst); 
		bstInsert(1, "one 6", bst); 
		bstInsert(1, "one 7", bst); 
		bstInsert(1, "one 8", bst); 
		bstInsert(1, "one 9", bst); 
		
		showbst("Original bst", bst);  
		
		bstInsert(20, " 20", bst); 
		showbst("bst content after adding 20 ...", bst); 
		bstInsert(13, " 13", bst); 
		showbst("bst content after adding 13 ...", bst); 
		bstInsert(19, " 16", bst); 
		showbst("bst content after adding 19 ...", bst); 
		bstInsert(7, " 7 1", bst); 
		showbst("bst content after adding 7 ...", bst); 
		bstInsert(7, " 7 2", bst); 
		showbst("bst content after adding 7 ...", bst); 
		bstInsert(7, " 7 3", bst); 
		showbst("bst content after adding 7 ...", bst); 
		bstInsert(21, " 21", bst); 
		showbst("bst content after adding 21 ...", bst); 
		
		bst.remove(13); 
		showbst("bst content after removing 13 ...", bst); 


		showbstEntries("Entries in the bst with key 1", bst, 1); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
	
		
		showbstEntries("Entries in the bst with key 1", bst, 1);
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		
		showbstEntries("Entries in the bst with key 7", bst, 7);

		showbstEntries("Entries in the bst (visiting nodes in inorder)", bst); 
	}
	
	private static void showbstEntries(String msg, LinkedBST<Integer, String> bst, Integer key) {
		System.out.println("\n" + msg);
		for (Entry<Integer, String> e : bst.getAll(key))
			System.out.println(e); 
	}

	private static <K, V> void bstInsert(K key, V value, LinkedBST<K, V> bst) { 
		System.out.println("bst after inserting entry [key = " + key + ", " + " values = " + value + "]"); 
		bst.add(key, value);
		bst.display();
		
	}
	private static <K,V> void showbst(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		t.display(); 
	}
	private static <K,V> void showbstValues(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K, V> value : t) 
			System.out.println(value); 
	}

	private static <K,V> void showbstKeys(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : t) 
			System.out.println(e.getKey()); 
	}

	private static <K,V> void showbstEntries(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : t) 
			System.out.println(e); 
	}
	
}


package testerClasses;

import java.util.Comparator;

import interfaces.Entry;
import treeClasses.LinkedBST;

public class LinkedBSTTester2 {
	public static void main(String[] args) { 
		
		System.out.println("Tester 1 for LinkedBST.\n");
		
		LinkedBST<Integer, String> bst = 
			new LinkedBST<>(new NumberComparator1()); 
		
		
		bstInsert(50, "fifty", bst);
		bstInsert(2, "two", bst); 
		bstInsert(3, "three", bst); 
		bstInsert(4, "four", bst); 
		bstInsert(5, "five", bst); 
		bstInsert(6, "six", bst); 
		bstInsert(60, "sixty", bst);
		
		showbst("Original bst", bst);  
		
		
		
		showbstEntries("Entries in the bst (visiting nodes in inorder)", bst); 
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


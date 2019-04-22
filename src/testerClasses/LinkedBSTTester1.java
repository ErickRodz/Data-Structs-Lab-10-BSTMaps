package testerClasses;

import java.util.Comparator;

import interfaces.Entry;
import treeClasses.LinkedBST;

public class LinkedBSTTester1 {
	public static void main(String[] args) { 
		
		System.out.println("Tester 1 for LinkedBST.\n");
		
		LinkedBST<Integer, String> bst = 
			new LinkedBST<>(new NumberComparator1()); 
		bstInsert(1, "one", bst); 
		bstInsert(30, "thirty", bst); 
		bstInsert(13, "thirteen", bst); 
		bstInsert(40, "forty", bst); 
		bstInsert(-10, "minus ten", bst); 
		bstInsert(19, "nineteen", bst); 
		bstInsert(11, "eleven", bst);  
		bstInsert(4, "four", bst); 
		bstInsert(-5, "minus five", bst); 
		bstInsert(35, "thirty five", bst);  
		bstInsert(50, "fifty", bst);  
		bstInsert(25, "twenty five", bst);  
		bstInsert(17, "seventeen", bst);  
		bstInsert(8, "eight", bst);  
		bstInsert(9, "nine", bst);  
	 	bstInsert(10, "ten", bst);  
		bstInsert(7, "seven", bst);  
		bstInsert(23, "twenty three", bst);  
		bstInsert(16, "sixteen", bst);  
		bstInsert(14, "fourteen", bst);  
		bstInsert(47, "forty seven", bst);  
		bstInsert(29, "twenty nine", bst);  
		bstInsert(33, "thirty three", bst); 
		bstInsert(15, "fifteen", bst);  
		
		showbst("Original bst", bst);  
		
		bstInsert(19, "nineteen 2", bst); 
		showbst("bst content after adding 19 ...", bst); 
		
		bst.remove(13); 
		showbst("bst content after removing 13 ...", bst); 

		bst.remove(1);  
		showbst("bst content after removing 1 ...", bst); 
		
		
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


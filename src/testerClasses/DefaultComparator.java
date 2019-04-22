package testerClasses;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

	@Override
	public int compare(E e1, E e2) {
		return ((Comparable<E>) e1).compareTo(e2);
	} 
	
}


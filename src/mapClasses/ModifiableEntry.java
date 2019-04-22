package mapClasses;

import interfaces.Entry;

public interface ModifiableEntry<K, V> extends Entry<K, V> {
	V setValue(V value);     // sets the value to value, and returns old value
}

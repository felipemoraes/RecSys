package pa2;

import java.util.Comparator;

//Helper class, used to maintain and process order arraylist
public class KeyValue{
	Integer key;
	Double value;
	public KeyValue(Integer key, Double value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyValue(Integer key, Integer value){
		this.key = key;
		long tmp = (long) value;
		this.value = (double) tmp;
	}
}

// Implement a comparator for Helper class

class KeyValueComparator implements Comparator<KeyValue> {
    @Override
    public int compare(KeyValue s, KeyValue t) {
       int f = t.value.compareTo(s.value);
       return (f != 0) ? f : s.key.compareTo(t.key);
    }
}
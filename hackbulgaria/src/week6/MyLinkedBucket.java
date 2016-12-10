package week6;

import java.util.LinkedList;

public class MyLinkedBucket<K, V> implements BucketInterface<K, V> {
	private class Entry {
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public K getKey() {
			return key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public V getValue(){
			return value;
		}
	}

	public LinkedList<Entry> list;
	
	public MyLinkedBucket() {
		list = new LinkedList();
	}
	
	@Override
	public void add(K key, V value) {
		if(get(key) == null) {
			list.add(new Entry(key, value));
		} else {
			Entry target = getEntry(key);
			target.setValue(value);
		}
	}
	
	private Entry getEntry(K key) {
		for(Entry e : list) {
			if(e.getKey().equals(key)) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public void remove(K key) {
		list.remove(getEntry(key));
	}

	@Override
	public V get(K key) {
		Entry target = getEntry(key);
		V value = (target == null) ? null : target.getValue();
			
		return value;
	}

}
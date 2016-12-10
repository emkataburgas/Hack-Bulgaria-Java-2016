package week6;

public class Map<K, V> implements MapInterface<K, V> {
	BucketInterface<K, V>[] buckets;
	private double loadFactor;
	private int size;
	
	public Map() {
		
		size = 8;
		loadFactor = 5; 
		buckets = new LinkedBucket[size];
	}
	
	@Override
	public void put(K key, V value) {
		int bucketIndex = calculateBucketIndex(key);
		
		buckets[bucketIndex].add(key, value);
	}
	
	private 

	@Override
	public V get(K key) {
		int bucketIndex = calculateBucketIndex(key);

		return buckets[bucketIndex].get(key);
	}

	@Override
	public void remove(K key) {
		int bucketIndex = calculateBucketIndex(key);
		buckets[bucketIndex].remove(key);
	}

	@Override
	public boolean contains(K key) {
		return (get(key) != null);
	}
	
	private int calculateBucketIndex(K key) {
		return key.hashCode() % size;
	}
}
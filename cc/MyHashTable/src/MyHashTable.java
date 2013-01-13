import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//also CC810
class MyKVPair<K,V>{
    public K key;
    public V value;
}

public class MyHashTable<K,V>{
    
    private final int BucketSize = 10;
    ArrayList<LinkedList<MyKVPair<K,V>>> buckets;
    
    
    public MyHashTable(){
        buckets =  new ArrayList<LinkedList<MyKVPair<K,V>>>(10);
        //initialize and pad the arraylist
        for(int i = 0; i < BucketSize; i ++){
            buckets.add(new LinkedList<MyKVPair<K,V>>());
        }
    }

    public void put(K key, V value){
        MyKVPair<K,V> kvPair = new MyKVPair<K,V>();
        kvPair.key = key;
        kvPair.value = value;

        int hashValue = ((Object)key).hashCode();
        int bucketNumber = hashValue % BucketSize;
        
        //get or create the LinkedList
        LinkedList<MyKVPair<K,V>> ll = buckets.get(bucketNumber);
        ll.add(kvPair);

        return;
    }

    public V get(K key){

        int bucketNumber = key.hashCode() % BucketSize;
        LinkedList<MyKVPair<K,V>> ll = buckets.get(bucketNumber);
        Iterator<MyKVPair<K,V>> iter = ll.iterator();
        while(iter.hasNext()){
            MyKVPair<K,V> pair = iter.next();
            if(pair.key.equals(key))
                return pair.value;
        }
        return null;
    }


    //test
    public static final void main(String[] args){
        MyHashTable<String, Integer> hash = new MyHashTable<String, Integer>();
        hash.put("yoyo", 29);
        hash.put("yoyoyo", 30);

        System.out.println( hash.get("yoyo"));
        System.out.println( hash.get("yoyoyo"));

        return;
    }

}

import java.util.LinkedList;
import java.util.PriorityQueue;


class PairSum implements Comparable<PairSum>{
    public int value1;
    public int value2;
    public int sum;
    public int index1;
    public int index2;
    public PairSum(int _value1, int _value2, int _index1, int _index2){
        value1 = _value1;
        value2 = _value2;
        sum = value1 + value2;
        index1 = _index1;
        index2 = _index2;
    }
    
    public int compareTo(PairSum another){
        if(this.sum < another.sum)
            return -1;
        else if(this.sum == another.sum)
            return 0;
        else
            return 1;
    }
    
    public String toString(){
        String res = "[ " + value1 + "," + value2 + " " + sum + " ]";
        return res;
    }
}

public class SortSum{
    
    
    public static LinkedList<PairSum> sortSum(int[] array1, int[] array2){
        LinkedList<PairSum> res = new LinkedList<PairSum>();
        PriorityQueue<PairSum> heap = new PriorityQueue<PairSum>();
        
        heap.offer(new PairSum(array1[0],array2[0],0,0));
        while(heap.size() != 0){
            PairSum popped = heap.poll();
            res.add(popped);
            int i = popped.index1;
            int j = popped.index2;
            if( (i < array1.length) && (j < array2.length - 1)){
                heap.offer(new PairSum(array1[i], array2[j+1], i, j + 1));
            }    
            
            if( (i < array1.length - 1) && (j < array2.length)){
                heap.offer(new PairSum(array1[i+1], array2[j], i+1, j));
            }
        }
        
        return res;
    }
    
    public static void main(String[] args){
        
        int[] array1 = {1,3,6};
        int[] array2 = {4,5,6};
        
      
        System.out.println(SortSum.sortSum(array1, array2));
        
        return;
    }
}

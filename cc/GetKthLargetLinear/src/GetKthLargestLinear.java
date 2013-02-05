import java.util.Arrays;




//get Kth largest item in an array or a linked list
//
//to simplify the problem, using array.
//but same approach could be done with linked list
//with no difference

public class GetKthLargestLinear{
    
    public static int getKthLargest(int[] array, int k){
        // K is from 1
        if(array == null || k > array.length)
            return Integer.MIN_VALUE;

        //traverse, to get the min and max value
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i : array){
            if(i > max)
                max = i;
            if(i < min)
                min = i;
        }

        //get the offset so that the bucket's index will be stored from 0
        int offset =  min - 0;
        int[] buckets = new int[max - min + 1];
        Arrays.fill(buckets, 0);
        for(int i : array){
            buckets[i - offset] ++;        
        }

        //the kth largest is actuallya (size - k)th element
        int counter = 0;
        int stopBucket = 0;
        /*
        for(int i = buckets.length -1; i >= 0; i --){
            counter += buckets[i];
            if(counter >=k){
                stopBucket = i;
                break;
            }
        }
        */

        for(int i = 0; i < buckets.length; i ++){
            counter += buckets[i];
            
            if(counter >= array.length - k + 1){
                stopBucket = i;
                break;
            }
        }
        return stopBucket + offset;
    }
    

    public static void main(String[] args){
        
        int array[] = {1,2,3,4,5,6,7,8,8,8,9};
        System.out.println(GetKthLargestLinear.getKthLargest(array,1));
        return;
    }
}

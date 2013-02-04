import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;



//Given an unsorted array of integers n. Return 'true' if the sum of any
//integers from that array matches with the target value else return false.


//my brute force solution:
//get all subsets and their sums
//and hash them into a hashtable
//so, when we want to seek the sum
//look up the hash table

public class SubsetSum{
    
    public static HashMap<Integer,LinkedList<LinkedList<Integer>>> sumHash = new HashMap<Integer, LinkedList<LinkedList<Integer>>>();

    public static int getSum(LinkedList<Integer> list){
        int sum = 0;
        for(Integer i : list)
            sum += i;
        return sum;
    }

    //returns a subset combinations
    public static LinkedList<LinkedList<Integer>> hashAllSubsums(int[] array, int start){
        //stop condition
        if(start == array.length){
            LinkedList<LinkedList<Integer>> subsets = new LinkedList<LinkedList<Integer>>();
            subsets.add(new LinkedList<Integer>());
            return subsets;
        }

        //else calc the sums and hash it, and return the subset to upper layers
        LinkedList<LinkedList<Integer>> nextSubsets = hashAllSubsums(array, start + 1);
        LinkedList<LinkedList<Integer>> subsets = (LinkedList<LinkedList<Integer>>)nextSubsets.clone();
        for(LinkedList<Integer> subset : nextSubsets){
            int sum = getSum(subset) + array[start];
            LinkedList<Integer> toAdd = (LinkedList<Integer>)subset.clone();
            toAdd.add(array[start]);
            subsets.add(toAdd);
            //put the value into hash map
            if(sumHash.containsKey(sum)){
                sumHash.get(sum).add(toAdd);
            }
            else{
                sumHash.put(sum, new LinkedList<LinkedList<Integer>>());
                sumHash.get(sum).add(toAdd);
            }
        }
        return subsets;
    }

    public static boolean getSubsetSum(int[] array, int sum){
        hashAllSubsums(array, 0);
        LinkedList<LinkedList<Integer>> subsets = sumHash.get(sum);
        if(subsets == null)
            return false;
        
        for(LinkedList ll : subsets)
            System.out.println(ll);

        return true;
    }


    //Dynamic programming solution
    //sort the array first
    //http://en.wikipedia.org/wiki/Subset_sum
    //it only returns true or false
    public static boolean whetherSubSum(int[] array, int sum){
        //get the sum boundries
        int maxSum = 0;
        int minSum = 0;
        for(int i = 0; i < array.length; i ++){
            if(array[i] > 0)
                maxSum += array[i];
            else
                minSum += array[i];
        }
        

        //create a two dimensional array to store
        //(n,k) = boolean
        //n is the last index of the portion 
        //k is the portion sum randing from min to max
        //take the offset
        int offset = 0 - minSum;
        //so there is an offset
        //
        boolean[][] portionSolutions = new boolean[array.length][maxSum + offset];
        //sort the array
        Arrays.sort(array);

        //dynamic programming
        //scan by line, and build from base
        //special calculation for first row
        for(int i = 0; i < maxSum + offset; i ++){
            portionSolutions[0][i] = (array[i] == (i - offset));
        }

        //then for the following rows
        for(int i = 1; i < array.length; i ++){
            for(int j = 0; j < maxSum + offset; j ++){
                portionSolutions[i][j] = portionSolutions[i-1][j] || (array[i] == (i - offset)) || portionSolutions[i-1][j-array[i]];           
            }
        }

        //then retrive the final answer
    
        return portionSolutions[array.length -1][sum + offset];
    }
    
            
    public static void main(String[] args){
        
        int array[] = {-5,6,7,1,0,12,5,-6,100};
        SubsetSum.getSubsetSum(array, 13);
        return;
    }
}

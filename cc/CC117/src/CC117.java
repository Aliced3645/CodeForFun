import java.util.ArrayList;
import java.util.LinkedList;


//simplify the probelm into a small problem which aims to get longest
//increasing sequence within an array
//
//
public class CC117{
    
    
    //save subproblems
    public static ArrayList<LinkedList<Integer>> subSequences;

    public static LinkedList<Integer> getLongestIncreasingSubsequence(int[] array){
        int length = array.length;
        subSequences = new ArrayList<LinkedList<Integer>>(length);
        
        //do the recursive decision
        for(int i = 0; i < length; i ++){

            LinkedList<Integer> previousLongest = null;
            int longestLength = Integer.MIN_VALUE;

            for(int j = 0; j < i; j ++){
                if(array[j] <= array[i]){
                    if( subSequences.get(j).size() > longestLength){
                        previousLongest = subSequences.get(j);
                        longestLength = previousLongest.size();
                    }
                }
            }

            //update the longest into the arrayList
            if(previousLongest == null){
                previousLongest = new LinkedList<Integer>();
                previousLongest.add(array[i]);
                subSequences.add(i, previousLongest);
            }

            else{
                LinkedList<Integer> newList = new LinkedList();
                newList.addAll(previousLongest);
                newList.add(array[i]);
                subSequences.add(i, newList);
            }
        }
        
        int maxLength = Integer.MIN_VALUE;
        LinkedList<Integer> toReturn = null;

        for(LinkedList<Integer> ll : subSequences){
            if(ll.size() > maxLength){
                toReturn = ll;
                maxLength = ll.size();
            }
        }
        
        for(LinkedList<Integer> ll : subSequences){
            System.out.println(ll);
        }

        
        return toReturn;
    }

    public static void main(String[] args){
        
        int[] array = {2,1,2,5,1,2,5,4,7,10,5,2,6,7,1,9,11,13};
        LinkedList<Integer> increasingSubSequence = CC117.getLongestIncreasingSubsequence(array);
        System.out.println(increasingSubSequence);

        return;
    }
}

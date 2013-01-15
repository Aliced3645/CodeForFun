import java.util.HashSet;
import java.util.LinkedList;



//return all subsets of a set
//
//


public class CC94{
    
    public static LinkedList<LinkedList<Integer>> getAllSubSets(LinkedList<Integer> set){
        //base case: empty set
        if(set.isEmpty()){
            LinkedList<LinkedList<Integer>> toReturn = new LinkedList<LinkedList<Integer>>();
            toReturn.add(new LinkedList<Integer>());
            return toReturn;
        }

        //recursive calls
          //get the first element
        Integer head = set.getFirst();
        set.removeFirst();
        LinkedList<LinkedList<Integer>> subSets = getAllSubSets(set);
        LinkedList<LinkedList<Integer>> toReturn = (LinkedList<LinkedList<Integer>>)subSets.clone();
      
        for(LinkedList<Integer> ll : subSets){
            ll.add(head);
            toReturn.add(ll);
        }
        
        return toReturn;
    }

    //method2 : combinatotics
    //
    //
    public static LinkedList<Integer> convertIntToSet(int number, LinkedList<Integer> set){
        LinkedList<Integer> subset = new LinkedList<Integer>();
        for(int i = 0; i < set.size(); i ++ ){
            if( (number & 1) == 1 ) {
                subset.add(set.get(i));    
            }
            number = number >> 1;
        }
        return subset;
    }
    
    public static LinkedList<LinkedList<Integer>> getAllSubSets2(LinkedList<Integer> set){
        
        LinkedList<LinkedList<Integer>> allSubsets  = new LinkedList<LinkedList<Integer>>();
        int max = 1 << set.size();
        for(int i = 0; i < max; i ++ ){
            allSubsets.add(convertIntToSet(i, set));
        }
        return allSubsets;
    }

    public static final void main(String [] args){
        return;
    }
    
}

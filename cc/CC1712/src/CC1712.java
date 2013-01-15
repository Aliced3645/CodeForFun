import java.util.HashMap;
import java.util.LinkedList;




class Pair{
    int index1;
    int index2;

    
    public Pair(int index1, int index2){
        this.index1 = index1;
        this.index2 = index2;
    }

}

//duplicate values?
//simple int-to-int hash not applicable

public class CC1712{
    
    public static LinkedList<Pair> findPairs(int sum, int[] array){
        LinkedList<Pair> ll = new LinkedList<Pair>();
        HashMap<Integer, LinkedList<Integer>> hash = new HashMap<Integer, LinkedList<Integer>>();
        
        for(int i = 0; i < array.length; i ++){
            //put to the hashmap
            int value = array[i];
            if(!hash.containsKey(value)){
                LinkedList<Integer> list = new LinkedList<Integer>();
                list.add(i);
                hash.put(value, list);
            }
            else{
                LinkedList<Integer> list = hash.get(value);
                list.add(i);
            }

            //find if sum's pair occurs
            //Take special care if value = sum / 2
            //then if should avoid itself
            if(hash.containsKey(sum - value)){
                LinkedList<Integer> list = hash.get( sum - value);
                for(Integer j : list){
                    if(i != j)
                        ll.add(new Pair(i,j));
                }
            }
        }
        return ll;
    }
    

    //method2: sort the array, but wont work for duplicate values
    public static final void main(String[] args){
        int[] array = {1,2,3,4,5,6,5,4,3};
        
        return;
    }
}

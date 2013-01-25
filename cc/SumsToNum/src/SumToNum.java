import java.util.LinkedList;


//print all possible sum combinations to a given number
//
//
public class SumToNum{

    public static LinkedList<LinkedList<Integer>> getSumPaths(int number){

        if(number == 0){
            LinkedList<LinkedList<Integer>> lls = new LinkedList<LinkedList<Integer>>();
            lls.add(new LinkedList<Integer>());
            return lls;
        }
        
        LinkedList<LinkedList<Integer>> toreturn = new LinkedList<LinkedList<Integer>>();
        for(int i = 1; i <= number; i ++){
            LinkedList<LinkedList<Integer>> llreturn = getSumPaths(number - i);
            for(LinkedList<Integer> ls : llreturn){
                ls.add(i);
                toreturn.add(ls);
            }
            
        }
        return toreturn;
    }

    
    public static void main(String[] args){
        
        LinkedList<LinkedList<Integer>> ls = SumToNum.getSumPaths(10);
        
        System.out.println(ls);
        return;
    }
}

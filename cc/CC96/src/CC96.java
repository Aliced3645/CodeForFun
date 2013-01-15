import java.util.HashSet;
import java.util.LinkedList;


//print all possible n pair parentheness
//
//
public class CC96{
    public static String insertAt(int index, String string, String toInsert){
        String toReturn = "";
        String leftPart = string.substring(0, index);
        String rightPart = string.substring(index,string.length());
        toReturn = leftPart + toInsert + rightPart;
        return toReturn;
    }

    public static HashSet<String> getAllParentheses(int size){
        HashSet<String> toReturn = new HashSet<String>();
        if(size == 0){
            toReturn.add("");
            return toReturn;
        }
        
        HashSet<String> subSolutions = getAllParentheses(size - 1);
        for(String subSolution : subSolutions){
            //put in the first 
            toReturn.add(insertAt(0,subSolution, "()"));
            for(int i = 0; i < subSolution.length(); i++){
                if(subSolution.charAt(i) == '('){
                    toReturn.add(insertAt(i + 1, subSolution, "()"));
                }
            }
        }
        
        return toReturn;
    }
    

    //solution without duplicates
    public static void getAllParentheses(LinkedList<String> solutions, int leftRem, int rightRem, String string){
        

        if(leftRem > rightRem || leftRem < 0 || rightRem < 0)
            return;
        
        if(leftRem ==  0 && rightRem == 0){
            solutions.add(string);
            return;
        }
        
        if(leftRem > 0){
            getAllParentheses(solutions, leftRem - 1, rightRem, new String(string + "("));
        }

        if(rightRem > leftRem){
            getAllParentheses(solutions, leftRem, rightRem - 1, new String(string + ")"));
        }

    }

    public static LinkedList<String> getAllParentheses2(int size){
        LinkedList<String> solutions = new LinkedList<String>();
        
        String str = "";
        getAllParentheses(solutions, size, size, str);

        return solutions;
    }
    
    public static final void main(String[] args){
       
       //HashSet<String> result = CC96.getAllParentheses(3);
       LinkedList<String> result = getAllParentheses2(3);
       for(String s : result){
        System.out.println(s);
       }
       return;    
    }
}

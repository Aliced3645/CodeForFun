import java.util.HashMap;

//using DP the solve the staris problem
//
//
public class CC91{
    
    public static HashMap<Integer, Long> subSolutions = new HashMap<Integer, Long>();
    public static long getSteps(int n){
        if(subSolutions.containsKey(n))
            return subSolutions.get(n);
        
        if(n < 0)
            return 0;
        if(n == 0){
            subSolutions.put(0,new Long(1));
            return 1;
        }
        
        long solutions = getSteps(n -1) + getSteps(n - 2) + getSteps(n -3);
        subSolutions.put(n, solutions);
        return solutions;
    }

    public static final void main(String[] args){
        System.out.println(CC91.getSteps(49));
    }
}

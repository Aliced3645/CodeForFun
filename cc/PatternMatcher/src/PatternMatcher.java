import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;


 class Answer{
        int length;
        int start;
        int end;
    }




public class PatternMatcher{
    
    
    public static Answer matchPatterns(TreeSet<String> patterns, String text){
        
        Answer toReturn = new Answer();
        toReturn.length = Integer.MAX_VALUE;

        HashMap<String, Integer> patternsHash = new HashMap<String, Integer>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int length = Integer.MAX_VALUE;
        Scanner scanner = new Scanner(text);
        int counter = 0;
        boolean allPatternsEncountered = false;
        while(scanner.hasNext()){
            String scanned = scanner.next();
            if( patterns.contains(scanned))
                patternsHash.put(scanned, counter);
            if(patternsHash.size() == patterns.size()){
                allPatternsEncountered = true;
            }

            if(allPatternsEncountered){
                //to see whether it causes the samllest pattern
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
                for(String s : patternsHash.keySet()){
                    System.out.println(patternsHash.get(s));    
                        if(patternsHash.get(s) > max)
                            max = patternsHash.get(s);
                        if(patternsHash.get(s) < min)
                            min = patternsHash.get(s);
                        length = max - min;
                }
                if(length < toReturn.length){
                    toReturn.length = length;
                    toReturn.start = min;
                    toReturn.end = max;
                }
            }
            counter ++;
        }
        return toReturn;
    }


    public static final void main(String[] args){
        TreeSet<String> patterns = new TreeSet<String>();
        patterns.add("Shu");
        patterns.add("Zhang");
        
        
        String text = "My name is Michael Zhang but also Shu You can call me Shu lalala Zhang or Zhang Shu hahaha";
        Answer res = PatternMatcher.matchPatterns(patterns, text);
        System.out.println(res.start + " " + res.end);
    }
}

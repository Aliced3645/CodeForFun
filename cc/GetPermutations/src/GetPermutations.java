import java.util.LinkedList;
import java.util.ListIterator;



public class GetPermutations{
    
    public static LinkedList<String> getPermutations(String string){
       
        if(string.length() == 0){
            LinkedList<String> res =  new LinkedList<String>();
            res.add("");
            return res;
        }
        
        LinkedList<String> ll = getPermutations(string.substring(1));
        //for the first letter to insert at all possible spaces
        ListIterator<String> iter = ll.listIterator();
        LinkedList<String> toreturn = new LinkedList<String>();
        while( iter.hasNext()){
            String substring = iter.next();
            StringBuffer subStringBuilder = new StringBuffer(substring);
            for(int i = 0; i <= substring.length(); i ++){
                subStringBuilder.insert(i, string.charAt(0));
                toreturn.add(subStringBuilder.toString());
                subStringBuilder.deleteCharAt(i);
            }
        }
        //force garbage collection
        ll = null;
        return toreturn;
    }


    public static void main(String[] args){
        String s = "abcd";
        LinkedList<String> res = GetPermutations.getPermutations(s);
        System.out.println(res);
        return;
    }
}

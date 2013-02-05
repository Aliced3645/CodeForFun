import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;



//tree map / tree set sort the keys
//but how about the values?
//heap is a good option
public class CC112{
    
    public static String sortStringByChar(String string){
        char[] ac = string.toCharArray();
        Arrays.sort(ac);
        String toReturn = new String(ac);
        return toReturn;
    }

    public static LinkedList<String> sortStrings(String[] array){
        //TreeMap is sorted
        LinkedList<String> toReturn = new LinkedList<String>();
        TreeMap<String, LinkedList<String>> map = new TreeMap<String, LinkedList<String>>();
        for(String s : array){
            String anagramSorted = sortStringByChar(s);
            if(map.containsKey(anagramSorted)){
                map.get(anagramSorted).add(s);
            }
            else{
                LinkedList ll = new LinkedList<String>();
                ll.add(s);
                map.put(anagramSorted, ll);
            }
        }


        //traverse the treemap and put back in sorted order
        for(Entry<String, LinkedList<String>> entry : map.entrySet()){
            LinkedList ll = entry.getValue();
            toReturn.addAll(ll);
        }

        return toReturn;
    }


    public static void main(String[] args){
        String[] array = {"tako", "abcd", "dcba", "mmba", "soga"};
        System.out.println(CC112.sortStrings(array));
    }
}

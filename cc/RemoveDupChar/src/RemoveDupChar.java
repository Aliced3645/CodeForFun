import java.util.LinkedHashMap;
import java.util.Map.Entry;






public class RemoveDupChar{



    public static String removeDupChars(String string){
        StringBuilder toReturn = new StringBuilder();
        LinkedHashMap<Character,Integer> hash = new LinkedHashMap<Character,Integer>();
        for(int i = 0; i < string.length(); i ++){
            Character c = string.charAt(i);
            if(hash.containsKey(c))
                hash.put(c, hash.get(c) + 1);
            else{
                hash.put(c,1);
            }
        }
        for(Entry e : hash.entrySet()){
                toReturn.insert(toReturn.length(), e.getKey());
        }
        return toReturn.toString();
    }

    public static String removeConsecutiveDups(String string){
        //wont use hashmap
        if(string == null || string.length() == 0)
            return string;

        StringBuilder builder = new StringBuilder();
        char first = string.charAt(0);
        for(int i = 1; i < string.length(); i ++){
            if(string.charAt(i) != first){
                builder.insert(builder.length(),first);
                first = string.charAt(i);
            }
        }
        //at last
        builder.insert(builder.length(), first);

        return builder.toString();
    } 
    public static void main(String[] args){
        
        System.out.println(RemoveDupChar.removeDupChars("aaaaaaaffjwiqeorvlksaaafffd"));
        System.out.println(RemoveDupChar.removeConsecutiveDups("aaaaaaaffjwiqeorvlksaaafffd"));

        return;
    }

}

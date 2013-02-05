import java.util.HashMap;



public class FindFirstNonRepeated{
    
    public static char findFirstNonRepeated(String s){
        HashMap<Character,Integer> hash = new HashMap<Character,Integer>(); 
        for(int i = 0; i < s.length(); i ++){
            Character c = s.charAt(i);
            if(hash.containsKey(c)){
                hash.put(c, hash.get(c) + 1);
            }
            else
                hash.put(c, 1);
        }

        //second pass
        char toReturn = 'a';
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(hash.get(c) == 1){
                toReturn = c;
                break;
            }
        }

        return toReturn;
    }

    public static void main(String[] args){

        String s = "fjasdkljklwjreoiuwj";
        System.out.println(FindFirstNonRepeated.findFirstNonRepeated(s));
        return;
    }
}

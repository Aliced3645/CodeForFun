import java.util.HashMap;
import java.util.HashSet;


public class RemoveDupCharFromTwo{


    //try do it inplace
    public static void removeDup(String str1, String str2){
        //traverse string2 and hash all chars
        HashSet<Character> hash = new HashSet<Character>();
        for(int i = 0; i < str2.length(); i ++){
            
            if(!hash.contains(str2.charAt(i))){
                hash.add(str2.charAt(i));
            }
        }

        StringBuilder str1Builder = new StringBuilder(str1);
        int pointer = 0;
        for(int i = 0; i < str1Builder.length(); i ++ ){
            if(!hash.contains(str1Builder.charAt(i))){
                str1Builder.setCharAt(pointer, str1Builder.charAt(i));
                pointer ++;
            }
        }

        str1 = null;
        str1 = str1Builder.substring(0,pointer).toString();
        System.out.println(str1);
    }

    public static void main(String[] args){
            
        String str1 = "amazon";
        String str2 = "azn";
        
        RemoveDupCharFromTwo.removeDup(str1, str2);
        System.out.println(str1);
    }
}



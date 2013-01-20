//Implement an algotirhm to determine if a string has all unique characters
import java.util.HashMap;
import java.util.Scanner;
import java.io.InputStream;

public class CC11{
   
    //Using Hashtable to see duplicates
    public static boolean seeDupByHash(String string){
        boolean result = false;
        HashMap<Character, Boolean> hashmap = new HashMap<Character, Boolean>();
        for(int i = 0; i < string.length(); i ++){
            if(hashmap.containsKey(string.charAt(i)) == true)
                return true;
            else 
                hashmap.put(string.charAt(i), true);
        }
        return result;
    }
    
    //use no extra space to do it
    public static boolean seeDupNoHash(String string){
        boolean result = false;
        //O(n2)
        for(int i = 0; i < string.length(); i ++){
            for(int j = 0; j < i; j ++){
                if(string.charAt(i) == string.charAt(j))
                    return true;
            }
        }
        return result;
    }

    public static final void main(String[] args){
            System.out.println("Please input the target string");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            //System.out.println(CC11.seeDupByHash(string));
            System.out.println(CC11.seeDupNoHash(string));
 
    }

   

}

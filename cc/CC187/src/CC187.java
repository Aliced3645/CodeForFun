import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class MyString implements Comparable<MyString>{
    public String string;

    public MyString(String _string){
        this.string = _string;
    }

    public int compareTo(MyString another){
        if(this.string.length() < another.string.length())
            return -1;
        else if(this.string.length() == another.string.length())
            return 0;
        else
            return 1;
    }
}


class Comp implements Comparator<String>{
    public int compare(String s1, String s2){
        if(s1.length() > s2.length())
            return 1;
        else if(s1.length() == s2.length())
            return 0;
        else 
            return -1;
    }
}
public class CC187{
    

    public static HashMap<String, Boolean> stringHash = new HashMap<String, Boolean>();

    public static boolean whetherComposite(String string){
        //iteratively decide whetner a string has composition parts
        if(string.length() == 0)
            return true; // iterative to the end
        boolean answer = false;
        for(int i = 0; i < string.length(); i ++){
            //get the substring, lookup the hashmap whehter it lies in it
            String previousPart = string.substring(0, i + 1);
            if(stringHash.containsKey(previousPart)){
                //recursive do
                answer |= whetherComposite(string.substring(i+1));
            }
        }

        return answer;
    }
    
    public static String getLongestCompositedString(String[] list){
        String toReturn = "";
        
        //scan to record the longest
        for(String s : list){
            if(whetherComposite(s)){
                if(s.length() > toReturn.length())
                    toReturn = s;
            }
            else{
                //put into the hashmap
                stringHash.put(s, true);
            }
        }
        return toReturn;    
    }
    

    public static void sortStrings(String[] list){
        //construct a MyString
        /*
        MyString[] tempList = new MyString[list.length];

        for(int i = 0; i < list.length; i ++){
            tempList[i] = new MyString(list[i]);
        }

        //sort the tempList
        Arrays.sort(tempList);

        //copy back
        for(int i = 0; i < list.length; i ++){
            list[i] = tempList[i].string;
        }
        */

        //sort the string
        Arrays.sort(list, new Comp());
    }



    public static void main(String[] args){

        String[] list = {"cat", "banana", "dogwalker", "nana", "dog", "walk", "walker"};
        CC187.sortStrings(list);
        for(int i = 0; i < list.length; i ++){
            System.out.print(list[i] + " ");
        }
        System.out.println();

        String longest = CC187.getLongestCompositedString(list);
        System.out.println(longest);
        return;
    }
}

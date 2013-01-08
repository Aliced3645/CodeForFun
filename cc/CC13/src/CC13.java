import java.util.Arrays;

//decide if one string is the permutation of the other


public class CC13{
    
    //spr tjese twp strings. need extra O(n) space
    public static boolean whetherPermutation(String str1, String str2){
        //not the same length return directly
        if(str1.length() != str2.length())
            return false;
        //sort str1 and str2, then compare
        char[] ss1 = str1.toCharArray();
        Arrays.sort(ss1);
        char[] ss2 = str2.toCharArray();
        Arrays.sort(ss2);
        for(int i = 0; i < ss1.length; i ++){
            if(ss1[i] != ss2[i])
                return false;
        }
        return true;
    }


    //method2: count whether chars appear in same amount of times
    //extra O(1) space
    public static boolean whetherPermutation2(String str1, String str2){
        if(str1.length() != str2.length())
            return false;
       
        int[] charCounts = new int[256];
        for(int i = 0; i < str1.length(); i ++){
            int c = (int)str1.charAt(i);
            charCounts[c] ++ ;
        }
        
        for(int i = 0; i < str2.length(); i ++){
            int c = (int)str2.charAt(i);
            //if the length is the same, there must be char in str2 to appear
            //more times than str1 if they are not permutations
            if(--charCounts[c] < 0)
                return false;
        }
        return true;
    }

    public final static void main(String[] args){
        String str1 = "abcdefg";
        String str2 = "bcafged";
        System.out.println(CC13.whetherPermutation(str1, str2));
        System.out.println(CC13.whetherPermutation2(str1, str2));
        return;
    }
}

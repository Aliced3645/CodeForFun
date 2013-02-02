import java.util.Scanner;




//given a string, find the start position of the largest block of repeated
//chars
//

public class LargestCharBlocks{


    public static int getStart(String s){
        //never forget to check the null...
        if(s == null || s.length() == 0)
            return -1;
        
        int tempStartRecord = 0;
        int startRecord = 0;
        char previousChar;
        int maxLength = 0;
        int tempLength = 0;
        char[] string = s.toCharArray();

        previousChar = string[0];
        startRecord = 0;
        maxLength = 1;
        tempLength = 1;
        for(int i = 1; i < string.length; i ++){
            if(string[i] == previousChar){
                tempLength ++;
            }
            else if(string[i] != previousChar){
                if(tempLength > maxLength){
                    maxLength = tempLength;
                    startRecord = tempStartRecord;
                }
                tempStartRecord = i;
                tempLength = 1;
                previousChar = string[i];
            }
        }
        return startRecord;
    }


    public static void main(String[] args){
        
        Scanner s = new Scanner(System.in);
        
        String string = s.next();

        System.out.println(LargestCharBlocks.getStart(string));
        return;
    }
}

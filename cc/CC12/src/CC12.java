import java.util.Scanner;


//reverse a string ( in the manner of char[])

public class CC12{
    
    public static void reverse(char[] charArray){
        int length = charArray.length;
        int mid = (length - 1) / 2;
        for(int i = 0; i <= mid; i ++){
            //swap(charArray[i], charArray[length -1 - i]);
            char t = charArray[i];
            charArray[i] = charArray[length - 1 - i];
            charArray[length - 1 - i] = t;
        }
        return;
    }

    public static final void main(String[] args){
        //read the input
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        char[] array = string.toCharArray();
        CC12.reverse(array);
        System.out.println(array);
        return;
    }
}

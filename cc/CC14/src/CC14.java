import java.util.Scanner;

//replace all spaces with "%20"


public class CC14{

    private static int getTrueLength(String string){
        int length = string.length();
        int spaceCount = 0;
        for(int i = 0; i < length; i ++)
            if(string.charAt(i) == ' ')
                spaceCount ++ ;
        length = length + 2 * spaceCount;
        return length;
    }

    public static char[] replaceSpacesWithLength(String string, int actualLength){
        char[] array = new char[actualLength];
        //copy string to array first
        char[] origarray = string.toCharArray();
        System.arraycopy(origarray, 0, array, 0, origarray.length);

        //go from the back
        //only manipulate array, origarray is useless now
        
        int back = actualLength - 1;
        int traverser = origarray.length - 1;
        while(traverser >= 0){
            if(array[traverser] != ' '){
                array[back] = array[traverser];
                back --;
                traverser --;
            }
            else{
                array[back] = '0';
                array[back - 1] = '2';
                array[back - 2] = '%';
                back -= 3;
                traverser --;
            }
        }
        return array;
    }
    
    public static char[] replaceSpaces(String string){
        int length = getTrueLength(string);
        return replaceSpacesWithLength(string, length);
    }

    public static final void main(String[] args){
        System.out.println("Please input the target string");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(replaceSpaces(string));
    }

}

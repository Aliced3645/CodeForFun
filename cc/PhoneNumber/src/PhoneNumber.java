



public class PhoneNumber{
    
 
    public static String[] map = {"yz", "1", "abc", "def","ghi", "jkl", "mno", "pqr", "stu", "vwx"};

    public static void doPrintPossibleCombinations(String number, int index, char[] string){
        
        if(index == number.length()){
            //at the end
            for(char c : string){
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        char headNumber = number.charAt(index);
        int mapIndex = headNumber - '0';
        String possibleInserts = map[mapIndex];
        
        for(int i = 0; i < possibleInserts.length(); i ++){
            string[index] = possibleInserts.charAt(i);
            doPrintPossibleCombinations(number, index + 1, string);
        }
        

    }
    
    public static void printPossibleCombinations(String number){
 
        char[] string = new char[number.length()];

        doPrintPossibleCombinations(number, 0, string);

    }

    public static void main(String[] args){

        PhoneNumber.printPossibleCombinations("adf1");
        return;
    }
}

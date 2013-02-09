

//find longest substring palindrome within a string

public class SubPalindrome{
    

//method1: DP, O(n^2) + O(n^2)
    public static String subPalindromeDP(String string){
        if(string == null || string.length() == 0)
            return null;

        String palindrome = null;
        //build a matrix for DP
        int[][] table = new int[string.length()][string.length()];
        //initialize two base conditions
        for(int i = 0; i < string.length() -1; i ++){
            table[i][i] = 1;
            if(string.charAt(i) == string.charAt(i + 1)){
                table[i][i+1] = 2;
            }
        }
        table[string.length()-1][string.length() - 1] = 1;

        //traverse and construct the full table
        //build in diagnal direction
        for(int k = 2; k < string.length(); k ++){
            int i = 0;
            while(i + k < string.length()){
                if(table[i + 1][i + k - 1] != 0){
                    if(string.charAt(i) == string.charAt(i + k))
                        table[i][i+k] = table[i+1][i+k-1] + 2;
                    else
                        table[i][i + k] = 0;
                }
                else{
                    table[i][i+k] = 0;
                }
                i ++;
            }
        }

        //traverse the whole table to get the longest
        int maxLength = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i < string.length(); i ++){
            for(int j = i; j < string.length(); j++){
                if( table[i][j] > maxLength){
                    maxLength = table[i][j];
                    start = i;
                    end = j + 1;
                }
            }
        }


        palindrome = string.substring(start , end);
        return palindrome;
    }


    //method2: non DP, O(n^2) + O(1)
    public static String expandAroundCenter(String string, int center1, int center2){
        char c1 = string.charAt(center1);
        char c2 = string.charAt(center2);
        if(c1 != c2){
            return new String("");
        }
        else{
            while((center1 >= 0) && (center2 < string.length()) &&  (string.charAt(center1) == string.charAt(center2))){
                center1 --;
                center2 ++;
            }
        }
        return string.substring(center1 + 1, center2);
    }
    

    public static String subPalindrome2(String string){

        int maxLength = 1;
        String palindrome = string.substring(string.length() - 1, string.length());
        
        for(int i = 0; i < string.length() - 1; i ++){
            String exp1 = expandAroundCenter(string,i,i);
            String exp2 = expandAroundCenter(string, i,i+1);

            String cmp = exp1.length() >= exp2.length()? exp1 : exp2;

            if(cmp.length() > maxLength){
                maxLength = cmp.length();
                palindrome = cmp;
            }
        }

        return palindrome;

    }


    //Manacher's algorithm which finds the longest palindrome in linear time
    
    public static void main(String[] args){
        System.out.println(SubPalindrome.subPalindromeDP("cababaeksuywuyuyuyuyu"));
        System.out.println(SubPalindrome.subPalindrome2("cababaeksuywuyuyuyuyu"));


        return;
    }
}

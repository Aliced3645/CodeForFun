


public class LCS{
    

    //subsequence
    public static String getLCS(String s1, String s2){
    
        String[][] table = new String[s2.length() + 1][s1.length() + 1];
        for(int i = 0; i <= s2.length(); i ++){
            table[i][0] = "";
        }
        for(int j = 0; j <= s1.length(); j ++){
            table[0][j] = "";
        }

        for(int i = 1; i <= s2.length(); i ++){
            for(int j = 1; j <= s1.length(); j ++){
                if(s1.charAt(j-1) == s2.charAt(i-1)){
                    table[i][j] = table[i-1][j-1] + s1.charAt(j-1);
                }
                else{
                    table[i][j] = (table[i-1][j].length() >= table[i][j-1].length())?
                                    table[i-1][j] : table[i][j-1];
                }
            }
        }

        return table[s2.length()][s1.length()];
    }
    
    //substring
    public static String getLCSubstring(String s1, String s2){
        String[][] table = new String[s2.length() + 1][s1.length() + 1];
        for(int i = 0; i <= s2.length(); i ++){
            table[i][0] = "";
        }
        for(int j = 0; j <= s1.length(); j ++){
            table[0][j] = "";
        }

        for(int i = 1; i <= s2.length(); i ++){
            for(int j = 1; j <= s1.length(); j ++){
                if(s1.charAt(j-1) == s2.charAt(i-1)){
                    table[i][j] = table[i-1][j-1] + s1.charAt(j-1);
                }
                else{
                    table[i][j] =   "";
                }
            }
        }
        
        String toReturn = null;
        int max = 0;
        for(int i = 1; i <= s2.length(); i ++){
            for(int j = 1; j <= s1.length(); j ++){
                if(table[i][j].length() > max){
                    max = table[i][j].length();
                    toReturn = table[i][j];
                }
            }
        }
        
        return toReturn;
    }

    public static final void main(String[] args){
        
        String s1 = "ACBDEGCEDBG";
        String s2 = "BEGCFEUBK";
        
        System.out.println(LCS.getLCS(s1,s2));

        String s3 = "I love you";
        String s4 = "Cuz I miss you";
        System.out.println(LCS.getLCSubstring(s3,s4));
        return;
    }
}

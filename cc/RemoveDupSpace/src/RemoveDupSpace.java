
public class RemoveDupSpace{
    public static void main(String[] args){
        String s = "a   b  c    d e";
        char[] string = s.toCharArray();
        int part = 0;
        boolean repeated = false;
        for(int i = 0; i < string.length; i ++){
            if(string[i] != ' '){
                repeated = false;
                string[part] = string[i];
                part ++;
            }
            else if(string[i] == ' '){
                if(!repeated){
                    string[part] = ' ';
                    part++;
                    repeated = true;
                }
            }
        }

        for(int i = part; i < string.length; i ++){
            string[i] =  ' ';
        }
        
        System.out.println(new String(string));
        return;
    }
}

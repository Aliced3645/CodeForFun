import java.util.Scanner;



public class CC15{

    public static String compress(String input){
        //String Buffer
        StringBuffer sb = new StringBuffer();
        int count = 1;
        char current = input.charAt(0);
        for(int i = 1; i < input.length(); i ++){
            if(input.charAt(i) == current)
                count ++;
            else{
                sb.append(current);
                sb.append(count);
                current = input.charAt(i);
                count = 1;
            }
        }
        sb.append(current);
        sb.append(count);
        String compressed = sb.toString();
        return input.length() >= compressed.length()? compressed : input;
    }
    public static final void main(String[] args){
            System.out.println("Please input the target string");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            System.out.println(CC15.compress(string));           
    }

}


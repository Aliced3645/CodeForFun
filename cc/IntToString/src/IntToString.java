import java.util.Stack;




public class IntToString{
    

    public static String intToString(int number){
        StringBuilder string = new StringBuilder();   
        int temp = number;
        while(temp != 0){
            int remix = temp % 10;
            //string.append(remix + '0');
            string.insert(0, remix);
            temp = temp / 10;
        }
        
        return string.toString();
    }


    public static int reverseInt(int number){
        String numberString = intToString(number);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < numberString.length(); i ++){
            stack.push(numberString.charAt(i) - '0');   
        }
        //pop and get
        int res = 0;
        while(!stack.isEmpty()){
            int popped = stack.pop();
            res = res * 10 + popped;
        }

        return res;
    }
    public static void  main(String[] args){
        
        System.out.println(IntToString.reverseInt(12312354));
        
        
        return;
    }
}

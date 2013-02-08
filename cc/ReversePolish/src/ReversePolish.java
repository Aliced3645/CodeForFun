import java.util.Stack;





public class ReversePolish{
    

    static boolean isNumber(char c){
        return c >= '0' && c <= '9';   
    }

    public static int calcReversePolish(String expression) throws Exception{
        
        if(expression == null || expression.length() == 0)
            return Integer.MIN_VALUE;
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < expression.length(); i ++){
            char c = expression.charAt(i);
            if(isNumber(c)){
                stack.push(c - '0');
            }
            else{
                //c is an operator
                if(stack.size() < 2){
                    return Integer.MIN_VALUE;
                }
                //enough number (2) for operation
                int number1 = stack.pop();
                int number2 = stack.pop();
                int ans = 0;
                switch(c){
                    case '+':
                        ans = number1 + number2;
                        break;
                    case '-':
                        ans = number1 - number2;
                        break;
                    case '*':
                        ans = number1 * number2;
                        break;
                    case '/':
                        ans = number1 / number2;
                        break;
                }
                stack.push(ans);
            }
        }

        if(stack.size() == 1)
            return stack.pop();
        else
            return Integer.MIN_VALUE;
    }

    public static void main(String[] args){
        
        String expression = "12+34*-";
        try{
        System.out.println(ReversePolish.calcReversePolish(expression));
        }catch(Exception e){
            ;
        }

    }
}

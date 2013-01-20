


public class BaseLog2{
    
    public static int getBaseLog2(double number){
        int res = 0;
        int numberToCalc = (int)Math.abs(number);
        while(numberToCalc != 0){
            numberToCalc >>= 1;
            res ++;
        }
        if(res > 0)
            res --;
        return res;
    }
    public static void main(String[] args){
        
        System.out.println(BaseLog2.getBaseLog2(256));
        return;
    }
}

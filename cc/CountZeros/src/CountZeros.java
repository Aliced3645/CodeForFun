



public class CountZeros{
    
    public static int countZeros(int number){
        if(number <= 0)
            return -1;
        int zeroCounts = 0;
        for(int i = 1;i <=number; i ++){
            int temp = i;
            while(temp % 5 == 0){
                temp = temp / 5;
                zeroCounts ++;
            }
        }
        return zeroCounts;
    }


     public static String parity(String msg)
   {
       String parityStr = null;
       int n = msg.hashCode();
       switch( n % 2 )
       {
       case 0:
           parityStr = "even";
           break;
       case 1:
           parityStr = "odd";
           break;
       }
       return parityStr;
        }

    public static int countOneBit(int integer){
        int count = 0;

        int temp = integer;
        while(temp != 0){
            if((temp & 1) > 0)
                count ++;
            temp = temp >> 1;
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(CountZeros.countZeros(10));
        System.out.println(CountZeros.countOneBit(5));
        return;
    }
}

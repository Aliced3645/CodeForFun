


public class SquareRoot{

    public static double getSquareRoot(double number){
        double high = number;
        double low = 0;
        double diff = 0.01;
        double mid = (high + low) / 2.0;

        while( Math.abs( mid * mid - number ) >= diff){
            if(mid*mid < number){
                low = mid;
            }
            else if(mid * mid > number){
                high = mid;
            }
            mid = (high + low) / 2.0;
        }

        return mid;
        
    }
    public static void main(String[] args){
        
        int number = 49;
        System.out.println(SquareRoot.getSquareRoot(number));
        return;
    }

}

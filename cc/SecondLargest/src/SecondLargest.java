


public class SecondLargest{
    

    public static int getSecondLargest(int[] array){
        
        //not possible to get the second biggest member
        if(array.length <= 1)
            return -1;
        
        int max1,max2;
        if(array[0] >= array[1]){
            max1 = array[0];
            max2 = array[1];
        }
        else{
            max1 = array[1];
            max2 = array[0];
        }

        for(int i = 2; i < array.length; i ++){
            if(array[i] > max1){
                max2 = max1;
                max1 = array[i];
            }
            else if(array[i] > max2){
                max2 = array[i];
            }
        }
        return max2;
    }


    public static void main(String[] args){
        int[] array = {3,6,7,9,8,5,4,3,1,10,12};
        System.out.println(SecondLargest.getSecondLargest(array));
        return;
    }
}

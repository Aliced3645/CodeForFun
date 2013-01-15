

public class RadixSort{   
    
    public static void radixSort(int[] array, int low, int high, int bit){
    
        if(bit < 0 || low < 0 || high > array.length -1 || low > high)
            return;
        
        int lowT = low;
        int highT = high;

        while(lowT < highT){
            int lowB = (array[lowT] & (1 << bit));
            int highB = (array[highT] & ( 1<< bit));
            //System.out.println(array[lowT] + " " + array[highT]);
            if(lowB == 0)
                lowT ++;
            
            if(highB > 0)
                highT --;
            
            if(lowB > 0 && highB == 0){
                int temp = array[lowT];
                array[lowT] = array[highT];
                array[highT] = temp;
                lowT ++;
                highT --;
                }
            }

            lowT = ( array[lowT] & (1 << bit)) > 0 ? lowT - 1 : lowT;
            for(int i  : array){
                System.out.print(i + " ");
            }
            System.out.println();

            radixSort(array, low, lowT, bit - 1);
            radixSort(array, lowT + 1, high, bit - 1);
        
    }



    public static final void main(String[] args){
        int array[] = {9,8,7,6,5,4,3,2,1};
        RadixSort.radixSort(array, 0, array.length - 1, 4);
        for(int i  : array){
            System.out.print(i + " ");
        }

        return;
    }

}

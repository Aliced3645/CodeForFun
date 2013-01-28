import java.util.Arrays;





public class MergeSort{


    
    public static void mergeSort(int[] array, int low, int high){

        if(low < high){
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            //merge
            int[] helper = new int[array.length];
            for(int i = 0; i < helper.length; i ++)
                helper[i] = array[i];
            int i = low;
            int j = mid + 1;
            //a mistake point
            int pointer = low;
            while( i <= mid && j <= high){
                if(helper[i] < helper[j]){
                    array[pointer] = helper[i];
                    i ++;
                }
                else{
                    array[pointer] = helper[j];
                    j ++;
                }
                pointer ++;
            }
            
            //copy the rest
            while(i <= mid){
                array[pointer] = helper[i];
                i ++;
                pointer ++;
            }

            while(j <= high){
                array[pointer] = helper[j];
                j ++;
                pointer ++;
            }

        }
                
    }



    public static void main(String[] args){
        int[] array = {4,3,1,10,9,23,1,5,23,17,10,99};
        MergeSort.mergeSort(array,0, array.length - 1);
        for(int i : array)
            System.out.print(i + " ");
        System.out.println();
        return;
    }
}

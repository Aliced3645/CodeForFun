

public class FindMissing{
    

    public static int findMissing(int[] array){
        int low = 0;
        int high = array.length - 1;
        int mid = (low + high) / 2;
        while(low < high){
            mid = (low + high) / 2;
            if(array[mid] == mid){
                low = mid + 1;
            }
            else if(array[mid] > mid){
                high = mid - 1;
            }
        }

        //find the missing one
        if(high == low) mid = high;

        if(mid > 0 && mid < array.length - 1){
            if(array[mid] - array[mid-1] != 1){
                return array[mid] - 1;
            }
            else
                return array[mid] + 1;
        }
        else if(mid == 0)
            return 0;
        else
            return array[mid] -1;
                
    }
    public static void main(String[] args){
        int array[] = {0,1,2,3,5,6,7};
        System.out.println(FindMissing.findMissing(array));
        return;
    }

}

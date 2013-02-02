



public class FindFirst{
    
    
    
    public static int findFirst(int[] array, int low, int high, int target){
        if(low > high)
            return -1;
        
        int mid = (low + high) / 2;
        if(array[mid] == target){
            //find the first occurance
            int i = mid -1;
            while(array[i] == target)
                i --;
            return i + 1;
        } 

        else if(array[mid] > target){
            return findFirst(array, low, mid - 1, target);
        }
        else if(array[mid] < target){
            return findFirst(array, mid+1, high, target);
        }
        
        
        return -1;
    }

    public static void main(String[] args){
        
        int[] array = {1,1,2,3,4,4,4,5,6,6,6,6,7,9};
        System.out.println(FindFirst.findFirst(array, 0, array.length -1, 6));
        return;
    }

}

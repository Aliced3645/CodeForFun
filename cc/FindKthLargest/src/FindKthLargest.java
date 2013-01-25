//[1,2,3]
//[2,3,4]


public class FindKthLargest{

    public static int getKthLargest(int[] arr1, int[] arr2, int k){
        int a1 = arr1.length - 1;
        int a2 = arr2.length - 1;
        
        //check the bounds...
        if( (k > arr1.length + arr2.length) || k < 1)
            return Integer.MIN_VALUE;
    
        int res = 0;
        int i = 1;
        while( a1 >= 0 && a2 >= 0){
            
            if(arr1[a1] >= arr2[a2]){
                res = arr1[a1];
                a1 --;
            }

            else{
                res = arr2[a2];
                a2 --;
            }

            if( i == k){
                return res;
            }
        

            i ++;
        }


        //if there are still unfinished...
            while(a1 >= 0){
                for(int j = i; j < k; j ++){
                    a1 --;
               }    
                return arr1[a1];
            }
    
            while(a2 >= 0){
                for(int j = i; j < k; j ++){
                    a2 --;
                }    
                return arr2[a2];
            }
    
            return -1;
        }
    

    public static void main(String[] args){
        int[] arr1 = {2,4,5,6,8};   
        int[] arr2 = {3,5,7,10,11};

        System.out.println(FindKthLargest.getKthLargest(arr1,arr2, 11));
    }

}


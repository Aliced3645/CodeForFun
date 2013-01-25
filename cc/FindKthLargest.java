//[1,2,3]
//[2,3,4]


int getKthLargest(int[] arr1, int[] arr2, int k){
    int a1 = arr1.length - 1;
    int a2 = arr2.length - 2;
    
    if(k > arr1.length + arr2.length)
        return -1;
    
    int res = 0;
    int i = 1;
    while( a1 >= 0 && a2 >= 0){
        
        if( i == k){
            return res;
        }
        
        if(arr1[a1] >= arr2[a2]){
            res = arr1[a1];
            a1 --;
        }
        else{
            res = arr2[a2];
            a2 --;
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
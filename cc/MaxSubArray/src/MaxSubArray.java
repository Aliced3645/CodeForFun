

class Answer{
    int start;
    int end;
    int sum;
}


public class MaxSubArray{
    
    
    public static Answer getMaxSubArrayLinear(int[] array){
        
        //record the overall answer
        Answer ans = new Answer();
        ans.start = -1;
        ans.end = -1;
        ans.sum = Integer.MIN_VALUE;

        //which is the "max ending here"
        Answer tempAns = new Answer();
        tempAns.start = 0;
        tempAns.end = 0;
        tempAns.sum = array[0];

        for(int i = 1; i < array.length; i ++){
            
            //there is no need to use the previous part if the previous sum is
            //smaller than 0!!!
            if(tempAns.sum < 0){
                tempAns.start = i;
                tempAns.sum = array[i];
                tempAns.end = i;
            }
            else{
                tempAns.sum += array[i];
                tempAns.end = i;
            }
            

            if(tempAns.sum > ans.sum){
                ans.sum = tempAns.sum;
                ans.start = tempAns.start;
                ans.end = tempAns.end;
            }
        }
        return ans;
    }

    public static Answer getMaxSum(Answer ans1, Answer ans2, Answer ans3){
        Answer temp  = ans1.sum >= ans2.sum? ans1 : ans2;
        return temp.sum > ans3.sum? temp : ans3;
    }


    public static Answer getMaxSubArrayDivide(int[] array, int start, int end){
        
        if(start == end){
            Answer res = new Answer();
            res.start = start;
            res.end = start;
            res.sum = array[start];
            return res;
        }
        
        else if(start > end){
            Answer res = new Answer();
            res.start = -1;
            res.end = -1;
            res.sum = Integer.MIN_VALUE;
            return res;
        }

        //divide and conquer
        int mid = (start + end) / 2;
        Answer resL = getMaxSubArrayDivide(array, start, mid - 1);
        Answer resR = getMaxSubArrayDivide(array, mid + 1, end);

        //get cross sum
        int leftProber = mid;
        int leftSum = Integer.MIN_VALUE;
        int leftSumTemp = 0;
        int leftSumStart = mid;
        while(leftProber >= 0){
            leftSumTemp += array[leftProber];
            if(leftSumTemp > leftSum){
                leftSum = leftSumTemp;
                leftSumStart = leftProber;
            }
            leftProber --;
        }

        int rightProber = mid + 1;
        int rightSum = Integer.MIN_VALUE;
        int rightSumTemp = 0;
        int rightSumEnd = mid + 1;

        while(rightProber <= end){
            rightSumTemp += array[rightProber];
            if(rightSumTemp > rightSum){
                rightSum = rightSumTemp;
                rightSumEnd = rightProber;
            }
            rightProber ++ ;
        }

        
        //combine two sums
        Answer resC = new Answer();
        resC.start = leftSumStart;
        resC.end = rightSumEnd;
        resC.sum = rightSum + leftSum;
        Answer toReturn = getMaxSum(resL, resR, resC);
        return toReturn;
    }
    
    //dynamic programming implementation
    //this problem cannot use this idea:
    //the max subarray of i elements could be deduced from max subarray of i -1
    //elements
    //reason: may not contiguous

    public static Answer[] partSums;
   /* public static void getMaxSubArrayDP(int[] array){
        partSums = new Answer[array.length];
        partSums[0].start = 0;
        partSums[0].end = 0;
        partSums[0].sum = array[0];

        for(int i = 0; i < array.length; i ++ ){
            if(array[i] > 0){
                if(partSums[i-1].sum < 0){
                    array[i].start = i;
                    array[i[.end = i;
                    array[
                }
                else{
                    
                }
            }
        }

    }
    */

    public static final void main(String[] args){
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Answer ans = MaxSubArray.getMaxSubArrayDivide(array, 0, array.length - 1);
        System.out.println("Start: " + ans.start + " End: " + ans.end + " Sum: " + ans.sum);
        return;
    }
}


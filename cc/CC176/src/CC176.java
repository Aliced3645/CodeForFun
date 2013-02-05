//Given an array of integers, find indices m and n such that if you sort
//elements m through n, the entire array would be sorted. Minimize n - m.
//
//


class Answer{
    int start;
    int end;


    public Answer(int _start, int _end){
        start = _start;
        end = _end;
    }

    public boolean onlyOneElement(){
        return start == end;
    }

}

public class CC176{
 

    //wrong solution
    //dont need recursive method
    public static Answer getNeedSortedWrong(int[] array, int start, int end){
        if(start > end)
            return null;
        if(start == end)
            return new Answer(start, end);
        
        int mid = ( start + end )  / 2;
        Answer ansL = getNeedSortedWrong(array, start, mid);
        Answer ansR = getNeedSortedWrong(array, mid + 1, end);
        //base situation
        if(ansL.onlyOneElement() && ansR.onlyOneElement()){
            if( array[ansL.start] > array[ansR.start] )
                return new Answer(ansL.start, ansR.start);
            else
                return null;
        }

        return null;
    }

    public static Answer getNeedSorted(int [] array){
        
        //get the three parts
        int leftEnd = -1;
        for(int i = 0; i < array.length - 1; i ++){
            if(array[i+1] < array[i]){
                leftEnd = i;
                break;
            }
        }
        if(leftEnd == -1)// already sorted
            return null;

        int rightEnd = -1;
        for(int i = array.length - 1; i > 0; i --){
            if(array[i-1] > array[i]){
                rightEnd = i;
                break;
            }
        }
        
        //the unsorted part is [leftEnd, rightEnd]
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i = leftEnd; i <= rightEnd; i ++){
            if(array[i] > max)
                max = array[i];
            if( array[i] < min)
                min = array[i];
        }

        //to probe the left part
        int expandedLeftEnd = 0;
        for(int i = leftEnd - 1; i >= 0; i --){
            if(array[i] <=  min){
                expandedLeftEnd = i + 1;
                break;
            }
        }

        int expandedRightEnd = array.length - 1;

        for(int i = rightEnd + 1; i < array.length; i ++){
            if(array[i] >= max){
                expandedRightEnd = i -1 ;
                break;
            }
        }

        return new Answer(expandedLeftEnd, expandedRightEnd);   
    }


    public static final void main(String[] args){
        //int[] array = {1,2,3,7,10,11,7,12,6,7,16,18,19};
        int[] array = {3,1,2,3,4,5,6};
        Answer res = CC176.getNeedSorted(array);
        for(int i : array)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("start: " + res.start + " end: " + res.end);
        return;
    }
}

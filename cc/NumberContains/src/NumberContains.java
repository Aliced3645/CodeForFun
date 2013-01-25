
//Give two ints, return index(0 based) of an int if it is present in another int. 
//For eg. int a = 34, int b = 12345. Function must return 2 since 34 starts in 12345 at index 2.


public class NumberContains{

	public static boolean compareThem(int large, int small){
	    int largeTemp = large;
	    int smallTemp = small;
	    
	    while(smallTemp > 0 && largeTemp > 0){
		int largeBit = largeTemp % 10;
		int smallBit = smallTemp % 10;
		if(largeBit == smallBit){
		    largeTemp /= 10;
		    smallTemp /= 10;
		}
		else
		    return false;
	    }
	    return true;
	}


	public static int getIndex(int large, int small){
		
	    int largeTemp = large;
	    int smallTemp = small;
	    while(largeTemp > 0){
		int largeBit = largeTemp % 10;
		int smallBit = smallTemp % 10;
		if(largeBit == smallBit){
		    //starts the comparison
		    boolean res = compareThem(largeTemp, smallTemp);
		    if(res){
		        //found
		        int index = 0;
		        int smallLength = 0;
                        while(largeTemp > 0){
		            largeTemp /= 10;
		            index ++;
		        }
                        while(smallTemp > 0){
                            smallTemp /= 10;
                            smallLength ++;
                        }
		        return index - smallLength;
		    }
		}
		largeTemp = largeTemp / 10;
	    }
            return -1;
	}

	public static void main(String[] args){
		int small = 34;
		int large = 12345;
		
		System.out.println(getIndex(large, small));		
		return;	
	}
}

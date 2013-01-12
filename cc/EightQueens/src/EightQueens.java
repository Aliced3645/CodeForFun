import java.util.ArrayList;
import java.util.Arrays;




public class EightQueens{
    
    public static ArrayList<int[]> solutions = new ArrayList<int[]>();
    public static int solutionCount = 0;
    
    private static void printRow(int col){
        if(col < 0){
            System.out.println("not valid col");
            return;
        }

        for(int i = 0; i < col; i ++)
            System.out.print("X" + " ");
        System.out.print("O" + " ");
        for(int i = col + 1; i < 8; i ++)
            System.out.print("X" + " ");
        System.out.println();
    }

    public static void printSolution(int[] array){
        if(array.length != 8){
            System.out.println("Wrong input array size");
            return;
        }

        for(int i  = 0; i  < array.length; i ++ )
            printRow( array[i]);
        System.out.println();
        
    }


    public static boolean checkPosition(int row, int col, int[] array){
        
        //check previous rows
        for(int r = 0; r < row; r ++ ){
            if(array[r] == col)
                return false;

            //check diagnol violence
            int rowabs = Math.abs(row - r);
            int colabs = Math.abs(col - array[r]);
            if(rowabs == colabs)
                return false;
        }
        return true;
    }

    public static void solveEightQueen(int row, int[] array){
        //termination
        for(int i = 0; i < 8; i ++){
            if(checkPosition(row, i, array)){
                array[row] = i;
                if(row == 7){
                    printSolution(array);
                    solutionCount ++ ;
                }
                else
                    solveEightQueen(row + 1, array);
             }
            else{
                //the traceback is done implicitly
            }
        }
        
    }

    public static final void main(String[] args){
        
        int[] array = new int[8];
        Arrays.fill(array, -1);
        solveEightQueen(0, array);
        EightQueens.printSolution(array);
        System.out.println(EightQueens.solutionCount);
        return;
    }


}

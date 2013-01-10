

public class CC17{
    

    public static final int ROWS = 5;
    public static final int COLS = 5;

    private static int mat [][] = new int[ROWS][COLS];
    
    private static void setRowZero(int row){
        for(int j = 0; j < COLS; j ++){
            mat[row][j] = 0;
        }
        return;
    }
    
    private static void setColZero(int col){
        for(int i = 0; i < COLS;  i  ++){
            mat[i][col] = 0;
        }

        return;
    }

    public static void setZeros(){
        //initializa the mat
        //step ommited
        boolean[] rowFlags = new boolean[ROWS];
        boolean[] colFlags = new boolean[COLS];
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLS; j ++){
                if(mat[i][j] == 0){
                    rowFlags[i] = true;
                    colFlags[j] = true;
                }
            }
        }

        //set all zeros
        //this approach may have duplicate access to elements
        for(int i = 0; i < ROWS; i ++){
            if(rowFlags[i] == true){
                setRowZero(i);                   
            }
        }

        for(int j = 0; j < COLS; j ++){
            if(colFlags[j] == true){
                setColZero(j);
            }
        }

        //another approach
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLS; j ++){
                if(rowFlags[i] || colFlags[j])
                    mat[i][j] = 0;
            }
        }

        return;
    }


    public static final void main(String[] args){
        
        return;
    }
}

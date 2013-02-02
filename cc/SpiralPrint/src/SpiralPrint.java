


public class SpiralPrint{
    
    //print the elements of matrix in the spiral order.
    //my idea is to use recursive, for each outer loop
    
    public static void printSpiral(int[][] mat, int layer){
        int up = layer;
        int buttom = mat.length - layer - 1;
        int left = layer;
        int right = mat[0].length - layer - 1;
        if(up > buttom+1 || left > right + 1)
            return; // termination condition
        
        //traverse the other frame, 
        //print up
        for(int i = left; i <= right; i ++){
            System.out.print( mat[up][i]+ " " );
        }
        //print right
        for(int i = up + 1; i <=buttom; i ++){
            System.out.print(mat[i][right] + " ");
        }

        //print buttom
        for(int i = right - 1; i >= left; i --){
            System.out.print(mat[buttom][i] + " ");
        }

        //print left
        for(int i = buttom - 1; i > up; i --){
            System.out.print(mat[i][left] + " ");
        }

        //recursive call for the next level
        printSpiral(mat, layer + 1);
    }

    public static void main(String[] args){
        
        int[][] mat = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        int[][] mat2 = {{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}};
        SpiralPrint.printSpiral(mat2,0);
        return;
    }
}

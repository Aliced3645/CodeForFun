

public class RotateMat{
    
    public static void rotate90Clockwise(int[][] mat){
        
        int length = mat.length;
        //i is the "layer"
        for(int i = 0; i < mat.length / 2; i ++){
            for(int j = i; j < mat.length - 1 - i; j ++){
                int temp = mat[i][j];
                mat[i][j] = mat[length-1-j][i];
                mat[length - 1 - j ][i] = mat[length - 1 - i][length - 1 - j];
                mat[length -1 - i][length - 1 - j ] = mat[j][length - 1 - i];
                mat[j][length - 1 - i] = temp;
            }
        }
    }


    public static void printMat(int[][] mat){
        for(int i = 0; i < mat.length; i ++){
            for(int j = 0; j < mat[0].length; j ++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        
        int[][] mat = new int[10][10];
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j ++){
                mat[i][j] = j;
            }
        }

        RotateMat.printMat(mat);

        System.out.println();

        RotateMat.rotate90Clockwise(mat);

        RotateMat.printMat(mat);
        return;
    }
}

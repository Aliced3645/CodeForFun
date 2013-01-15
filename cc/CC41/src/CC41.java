
class Node{
    Node left;
    Node right;
    int data;
}

public class CC41{
    

    public static int getHeight(Node root){
        if(root == null)
            return 0;
        
        int heightL = getHeight(root.left);
        int heightR = getHeight(root.right);
        if( heightL == -1 || heightR == -1)
            return -1;
        
        if( Math.abs(heightL - heightR) > 1)
            return -1;
        else
            return 1 + Math.max(heightL, heightR);
    }
    
       

    public static boolean decideBalanced(Node root){
        if(getHeight(root) == -1)
            return false;
        return true;
    }
}

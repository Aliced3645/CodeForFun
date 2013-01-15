import java.util.LinkedList;



class BiNode{
    public BiNode node1, node2;
    public int data;

    public BiNode(int _data){
        data = _data;
    }
}


public class CC1713{

    //based on in-order traversal
    //
    public static BiNode getTail(BiNode node){
        if(node == null)
            return null;

        BiNode n2 = node;
        while(n2.node2 != null)
            n2 = n2.node2;
        return n2;
    }

    public static void concat(BiNode first, BiNode second){
        if(first != null)
            first.node2 = second;
        if(second != null)
            second.node1 = first;
    }

    public static BiNode convert(BiNode root){
        if(root == null)
            return null;

        BiNode leftHead = convert(root.node1);
        BiNode rightHead = convert(root.node2);
        
        BiNode leftTail = getTail(leftHead);
        concat(leftTail, root);
        concat(root, rightHead);
        
        return leftHead == null ? root : leftHead;
    }
    

    public static BiNode constructTree(int[] array, int low, int high){
        if(low > high)
            return null;
        int mid = (low + high) / 2;
        BiNode node = new BiNode(array[mid]);
        node.node1 = constructTree(array, low, mid - 1);
        node.node2 = constructTree(array, mid + 1, high);
        return node;
    }
    
    public static void inOrderTraverse(BiNode root){
        if(root == null)
            return;
        inOrderTraverse(root.node1);
        System.out.print(root.data + " ");
        inOrderTraverse(root.node2);
    }

    
    public static void printByLevel(BiNode root){
        LinkedList<BiNode> traversing = new LinkedList<BiNode>();
        traversing.add(root);
        LinkedList<BiNode> toTraverse = new LinkedList<BiNode>();
        
        while( !traversing.isEmpty() ) {
            
            for(BiNode node : traversing){
                System.out.print(node.data + " ");
                if(node.node1 != null)
                    toTraverse.add(node.node1);
                if(node.node2 != null)
                    toTraverse.add(node.node2);
            }
            LinkedList<BiNode> temp = traversing;
            traversing = toTraverse;
            toTraverse = temp;
            toTraverse.clear();
            System.out.println();
        }
    }



    public static final void main(String[] args){
        
        //construct a binary search treee
        int[] array = {1,3,4,5,6,8};
        BiNode root = CC1713.constructTree(array, 0, array.length - 1 );
        CC1713.inOrderTraverse(root);
        System.out.println();
        CC1713.printByLevel(root);
        BiNode head = CC1713.convert(root);
        BiNode traverser = head;
        while(traverser != null){
            System.out.print( traverser.data + " ");
            traverser = traverser.node2;
        }
    }
}

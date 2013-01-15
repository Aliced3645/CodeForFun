import java.util.LinkedList;
import java.util.Stack;


enum Play{
    GOOD,BAD;
}


class BiNode{
    public BiNode node1, node2;
    public BiNode parent;
    public int data;

    public BiNode(int _data){
        data = _data;
        node1 = null;
        node2 = null;
        parent = null;
    }
}


public class BinaryTree{

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
        if(node.node1 != null)
            node.node1.parent = node;
        if(node.node2 != null)
            node.node2.parent = node;
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


    //check whether a binary tree is a binary search tree
    public static boolean whetherBSTNonTraversal(BiNode root){
        //non-recursive traversal to see whether always increasing
        Stack<BiNode> stack = new Stack<BiNode>();
        BiNode traverser = root;
        int previous = Integer.MIN_VALUE;
        while( !stack.empty() || traverser != null){
            if(traverser != null){
                stack.push(traverser);
                traverser = traverser.node1;
            }
            //no left
            else{
                BiNode popped = stack.pop();
                if( popped.data < previous)
                    return false;
                previous = popped.data;
                traverser = popped.node2;
            }
        }
        return true;
    }

    //check whether a binary tree is a binary search tree method2
    public static boolean whetherBST2(BiNode node, int max, int min){
        if(node == null)
            return true;
        if(node.data > max || node.data < min)
            return false;
        
        boolean leftResult = whetherBST2(node.node1, node.data, min);
        if(leftResult == false)
            return false;

        boolean rightResult = whetherBST2(node.node2, max, node.data);

        if(rightResult == false)
            return false;

        return true;
        
    }
    
    public static boolean whetherBST2(BiNode root){
        return whetherBST2(root, Integer.MAX_VALUE, Integer.MIN_VALUE);       
    }


    public static int getNextForBST(BiNode node){
        
        BiNode traverser = node;
        if(node.node2 != null){
            traverser = node.node2;
            while(traverser.node1 != null)
                traverser = traverser.node1;

            return traverser.data;
        }

        //no right subtree
        else if(node.node2 == null){
            traverser = node.parent;
            while(traverser != null){
                if(traverser.node1 == node)
                    return traverser.data;
                traverser = traverser.parent;
                node = node.parent;
            }
        }
            
        
        return Integer.MIN_VALUE;   
    }

    
    public static void printSingleSumPath(BiNode node, int sum, LinkedList<Integer> path){
    
        if(node == null)
            return;
        if(sum - node.data < 0)
            return;
        
        if(sum - node.data == 0){
            for(Integer i : path){
                System.out.print(i + " ");
            }
            System.out.print(node.data);
            System.out.println();
            return;
        }

        if(sum - node.data > 0){
            path.add(node.data);
            printSingleSumPath(node.node1, sum - node.data, (LinkedList<Integer>)path.clone());
            printSingleSumPath(node.node2, sum - node.data, (LinkedList<Integer>)path.clone());
        }
        return;
    
    }
    
    public static void printSumPaths(BiNode root, int sum){
        printSingleSumPath(root, sum, new LinkedList<Integer>());
        if(root != null){
            printSumPaths(root.node1, sum);
            printSumPaths(root.node2, sum);
        }

    }

    public static final void main(String[] args){
        
        //construct a binary search treee
        int[] array = {1,2,3,4,5,6};
        BiNode root = BinaryTree.constructTree(array, 0, array.length - 1 );
        BinaryTree.inOrderTraverse(root);
        System.out.println();
        BinaryTree.printByLevel(root);
        //BiNode head = BinaryTree.convert(root);
        //BiNode traverser = head;
        //while(traverser != null){
        //   System.out.print( traverser.data + " ");
        //    traverser = traverser.node2;
        //}
        System.out.println(BinaryTree.whetherBSTNonTraversal(root));
        System.out.println(BinaryTree.whetherBST2(root));
        //System.out.println(BinaryTree.getNextForBST(root.node2.node1));
        BinaryTree.printSumPaths(root, 6);
    }
}

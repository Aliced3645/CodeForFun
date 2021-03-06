import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


enum Play{
    GOOD,BAD;
}


class Node{
    public int value;
    public Node next;
    public Node(Node prev, int _value){
        this.value = _value;
        if(prev == null)
            return;
        prev.next = this;
        next = null;
        return;
    }
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


class Pair{
    int value1;
    int value2;
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
        if(root == null)
            return;

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

    
    public static int last = Integer.MIN_VALUE;
    public static boolean whetherBST3(BiNode node){
        if(node == null)
            return true;
        if(!whetherBST3(node.node1))
            return false;
        if(node.data < last)
            return false;
        last = node.data;
        if(!whetherBST3(node.node2))
            return false;

        return true;
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
    

    //find lowerest common ancestor
    //auxiliary function, returns whether the subtree contains the destined
    //value
    //use dynamic programming to store the intermediate results
    
    
    //the problem: we want to search for two values, so one hash is not
    //sufficient!
    //
    //store the value1 circumstances!!!
    public static HashMap<BiNode, Boolean> hash1 = new HashMap<BiNode, Boolean>();
    //store the value2 circumstances!!!
    public static HashMap<BiNode, Boolean> hash2 = new HashMap<BiNode, Boolean>();
    //it is indeed a preOrder search
    //which is a DFS
    public static boolean whetherContains(BiNode node, int target, int whichHash){
        
        HashMap<BiNode, Boolean> hash = (whichHash == 1? hash1 : hash2);
        if(node == null){
            return false;
        }
        //Look up the hash table first
        if(hash.containsKey(node))
            return hash.get(node);
        
        if(node.data == target){
            hash.put(node, true);
            return true;
        }
        else{
            boolean result = whetherContains(node.node1, target, whichHash) || whetherContains(node.node2, target, whichHash);
            if( ! hash.containsKey(node))
                hash.put(node, result);
            return result;
        }
    }


    //the function, returns the value of the common ancestor's value
    //wrong point: forget to establish hash for EACH VALUE
    public static Integer getCommonAncester(BiNode root, int value1, int value2){
        int res = Integer.MIN_VALUE;
        if(root == null)
            return res;
        
        boolean leftContains1 = whetherContains(root.node1, value1, 1);
        boolean leftContains2 = whetherContains(root.node1, value2, 2);
        if(leftContains1 == leftContains2){
            //both in the same side
            if(leftContains1==true){
                //in the left
                return getCommonAncester(root.node1, value1, value2);
            }
            else{
                return getCommonAncester(root.node2, value1, value2);
            }
        }
        
        else if(leftContains1 != leftContains2){
            //not in the same side, this is the common ancester
            //everytime found, empty the hash
            //hash.clear();
            return root.data;
        }

        return res;
    }


       
    
    //implement the insert and delete functions for binary tree
    //insert returns the root node
    public static BiNode insert(BiNode root, int value){
        if(root == null)
            return new BiNode(value);
        else{
            //root not null
            BiNode p = root;
            BiNode s = root;
            if(value > root.data)
                s = p.node2;
            else
                s = p.node1;

            while( s != null){
                p = s;

                if(value > s.data){
                    s = s.node2;
                }
                else{
                    s = s.node1;
                }
            }
            
            //find the final
            if( value >  p.data)
                p.node2 = new BiNode(value);

            else
                p.node1 = new BiNode(value);
        }

        return root;
    }
    

    //wrong point:
    //forget to return
    //delete a node with specified value
    //also returns the root
    public static BiNode delete(BiNode root, int value){
        //first get the node
        BiNode p = root;
        BiNode s = root;
        //get the node to delete
        //check root
        if(root.data  ==  value){
            s = root;
            p = null;
        }

        else{
            if( value > s.data)
                s = s.node2;
            else
                s = s.node1;

            while(s != null){
                if(s . data ==  value)
                    break;
                p = s;
                if(value > s.data){
                    s = s.node2;
                }
                else
                    s = s.node1;
            }
        }
        
        
        //delete operation basing on p and s
        //s is the target node to delete
        //p is the parent
        //no children at all, leaf node
        if( s. node1 == null && s.node2 == null){
            if(p != null){
                if( p.node1 == s)
                    p.node1 = null;
                else
                    p.node2 = null;
                
                return root;
            }

            else
                return null;
        }
        
        //has only left child
        if(s.node1 != null & s.node2 == null){
            if(p != null){
                if(p.node1 == s)
                    p.node1 = s.node1;
                else
                    p.node2 = s.node1;
                return root;
            }

            else{
                return s.node1;
            }
        }
        
    
        if(s.node2 != null){
            //get the successor
            BiNode successor = s.node2;
            BiNode successorParent = s;           
            while(successor.node1 != null){
                successorParent = successor;
                successor = successor.node1;
            }
            //replace s with value of successor
            s.data = successor.data;
            //delete successor 
            if(successorParent == s){
                successorParent.node2 = null;
            }
            else{
                //if successor has right child?
                successorParent.node1 = successor.node2; 
            }
            return root;
        }
        
        return null;
    }

    //convert a binary search tree to a heap

    public static int getBinaryTreeSize(BiNode node){
        if(node == null)
            return 0;
        return 1 + getBinaryTreeSize(node.node1) + getBinaryTreeSize(node.node2);
    }

    public static int arrayIndex = 0;
    public static void putBSTIntoArray(BiNode node, int[] heapArray){
        if(arrayIndex == heapArray.length)
            return;
        if(node == null) 
            return;
        putBSTIntoArray(node.node1, heapArray);
        heapArray[arrayIndex] = node.data;
        arrayIndex ++;
        putBSTIntoArray(node.node2, heapArray);

    }

    //a function to help construct heap
    //Pay attention to the offset here..
    public static BiNode heapConstruct(int[] heapArray, int index){
        if(index > heapArray.length - 1)
            return null;
        BiNode node = new BiNode(heapArray[index]);
        node.node1 = heapConstruct(heapArray, 2 * index + 1);
        node.node2 = heapConstruct(heapArray, 2 * index + 2);
        return node;
    }

    public static BiNode toHeap(BiNode root){
        //first to resume it to an array
        //in order to get the array size first traverse it
        int arraySize = getBinaryTreeSize(root);
        int[] heapArray = new int[arraySize];
        //in order traverser to put into heapArray
        putBSTIntoArray(root, heapArray);
        //reconstruct a heap(MIN heap)
        BiNode node = heapConstruct(heapArray, 0);
        return node;
    }
    

    //expected to get a logn solution
    public static int getKthSmallest(BiNode root, int k){
        
        
        return -1;
    }
    

    public static void getAll(BiNode root, LinkedList<Integer> list){
        if(root == null)
            return;
        getAll(root.node1, list);
        list.add(root.data);
        getAll(root.node2, list);
    }

    public static LinkedList<Integer> getAllArrays(BiNode root){
        LinkedList<Integer> ll = new LinkedList<Integer>();
        getAll(root,ll);
        return ll;
    }
    
    //O(n) and O(tree height) space consuption to find two nodes in the tree
    //whose sum is equal to x.   
    public static Pair findSum(BiNode root, int sum){
        
        //never forget to check.../
        if(root == null){
            return null;
        }

        else if(root != null && root.node1 == null && root.node2 == null){
            return null;
        }
        
        Pair res = new Pair();
        //create two stacks.
        //one is to store from the smallest,
        //the other is to store from the biggest.
        Stack<BiNode> stack1 = new Stack<BiNode>();
        Stack<BiNode> stack2 = new Stack<BiNode>();
        
        BiNode traverser = root;
        //special case..
        if(root.node1 == null){
            stack1.push(root);
            traverser = root.node2;
            while(traverser != null){
                stack2.push(traverser);
                traverser = traverser.node2;
            }
        }
        else if(root.node2 == null){
            stack2.push(root);
            traverser = root.node1;
            while(traverser != null){
                stack1.push(traverser);
                traverser = traverser . node1;
            }
        }
        else{
            while(traverser != null){
                stack1.push(traverser);
                traverser = traverser.node1;
            }
            traverser = root.node2;
            while(traverser != null){
                stack2.push(traverser);
                traverser = traverser.node2;
            }
        }
        
        //now true)
        while(true){
            BiNode node1 = stack1.peek();
            BiNode node2 = stack2.peek();
            System.out.println(node1.data + " " + node2.data);
            if(node1.data + node2.data == sum){
                res.value1 = node1.data;
                res.value2 = node2.data;
                return res;
            }
        
            if( node1 == root || node2 == root )
                break;

            if(node1.data + node2.data > sum){
                //move node2 to be smaller
                node2 = stack2.pop();
                traverser = node2.node1;
                if(traverser != null){
                    stack2.push(traverser);
                    traverser = traverser . node2;
                    while(traverser != null){
                        stack2.push(traverser);
                        traverser = traverser.node2;
                    }
                }
            }

            else if(node1.data + node2.data < sum){
                node1 = stack1.pop();
                traverser = node1.node2;
                if(traverser != null){
                    stack1.push(traverser);
                    traverser = traverser. node1;
                    while(traverser != null){
                        stack1.push(traverser);
                        traverser = traverser.node1;
                    }
                }
            }
        }

        return null;
    }
    

    //if all nodes have distinct values, use this functino to find node by
    //value
    public static BiNode getNode(int value, BiNode root){
        
        if(root == null)
            return null;
        BiNode res = getNode(value, root.node1);
        if(res != null) return res;
        if(root.data == value) return root;
        return getNode(value, root.node2);
    }


    
    //a sub problem/auxiliary function: 
    //find the path from root to a given BiNode with specified value

    public static boolean getPathtoTargetSub(BiNode node, int value, Stack<Integer> pathStack){
        if(node == null)
            return false;
        //pre order traverser
        if(node.data == value){
            //pathStack.push(value);
            return true;
        }

        if( getPathtoTargetSub(node.node1, value, pathStack)){
            pathStack.push(node.node1.data);
            return true;
        }

        else if(getPathtoTargetSub(node.node2, value, pathStack)){
            pathStack.push(node.node2.data);
            return true;
        }
        
        return false;
    }

    public static Stack<Integer> getPathtoTarget(int target, BiNode root){
        Stack<Integer> stack = new Stack<Integer> ();
        getPathtoTargetSub(root, target, stack);
        stack.push(root.data);
        //resume to a linkedList
        return stack;
    }
    
    
    public static BiNode findKDistanceUpperNode(BiNode node, int k, BiNode parent){
                
            return null;
    }

    public static LinkedList<BiNode> findKDistanceLowerNodes(BiNode node, int k){
            return null;
    }
    
    public static LinkedList<BiNode> findKDistanceNodes(BiNode node, int k){
        
        return null;
    }

    //convert a liniked list to binary search tree which is balanced
    public static BiNode listToBST(Node head){

        if(head == null)
            return null;
        if( head.next == null){
                return new BiNode(head.value);

        }
            
        //get the mid point
        Node slow = head;
        Node fast = head;
        Node slowPrev = null;
        
        while(fast != null && fast.next != null){
            if(slow == head){
                slow = slow.next;
                slowPrev = head;
            }
            else{
                slowPrev = slow;
                slow = slow.next;
            }

            fast = fast.next.next;
        }

        //now slow is the new root, and get two sub lists
        BiNode root = new BiNode(slow.value);

        slowPrev.next = null;
        root.node1 = listToBST(head);
        root.node2 = listToBST(slow.next);
        
        return root;

    }
    public static final void main(String[] args){
        
        //construct a binary search treee

        
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        BiNode root = BinaryTree.constructTree(array, 0, array.length - 1 );


        //BinaryTree.inOrderTraverse(root);
        //System.out.println();
        //BinaryTree.printByLevel(root);
        //BiNode head = BinaryTree.convert(root);
        //BiNode traverser = head;
        //while(traverser != null){
        //   System.out.print( traverser.data + " ");
        //    traverser = traverser.node2;
        //}
        //System.out.println(BinaryTree.whetherBSTNonTraversal(root));
        //System.out.println(BinaryTree.whetherBST2(root));
        //System.out.println(BinaryTree.getNextForBST(root.node2.node1));
        //BinaryTree.printSumPaths(root, 6);
        //System.out.println(BinaryTree.getCommonAncester(root, 1 , 6));
        

        //test insertion and deletion
        /*
        int [] array = {5, 1, 4, 3, 7, 10, 8, 12, 6};
        BiNode root = null;
        for(int i = 0 ; i < array.length; i ++){
            root = BinaryTree.insert( root, array[i]);
        }
        */

        //root = BinaryTree.delete(root,10);
        //BinaryTree.printByLevel(root);
        //root = BinaryTree.toHeap(root);
        BinaryTree.printByLevel(root);
        System.out.println(BinaryTree.getAllArrays(root));
        /*
        Pair p = BinaryTree.findSum(root,14);
        if(p != null){
            System.out.println("Pair: " + p.value1 + " " + p.value2);
        }
        */
        //get the path
        Stack<Integer> pathStack = BinaryTree.getPathtoTarget(6, root);
        while( !pathStack.isEmpty()){
            System.out.print(pathStack.pop() + " ");       
        }

        System.out.println(BinaryTree.whetherBST3(root));

        //construct a linked list
        Scanner scanner = new Scanner("1 2 3 4 5 6 7 8 9 10 11 13");
        Node head = null;
        Node prev = null;
        while(scanner.hasNext()){
            if(prev == null){
                head = new Node(prev, scanner.nextInt());
                prev = head;
            }
            else
                prev = new Node(prev, scanner.nextInt());
            
        }

        System.out.println();
        //ok, I want to turn this linked list into a binary tree
        BiNode convertedRoot = BinaryTree.listToBST(head);
        BinaryTree.printByLevel(convertedRoot);
    }

}

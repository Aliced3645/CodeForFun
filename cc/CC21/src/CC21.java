import java.util.HashMap;
import java.util.Scanner;


//remove duplicates in unsorted linked list
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

public class CC21{
    
    //with temp buffer
    public static void removeDuplicates(Node head){
        if(head == null)
            return;
        //use hash
        //O(1) lookup time
        HashMap<Integer,Boolean> hash = new HashMap<Integer, Boolean>();
        Node prev = head;
        Node later = head.next;
        hash.put(prev.value, true);
        while(later != null){
            if(hash.containsKey(later.value)){
                prev.next = later.next;
                later = prev.next;
                /* WRONG CODE
                 * SKIPs some nodes!!!!
                prev = prev.next;
                if(prev != null)
                    later = prev.next.next;
                else
                    later = null;
                */
            }
            else{
                hash.put(later.value, true);
                prev = later;
                later = later.next;
            }
            
        }
        
    }


    //with no temp buffer
    public static void removeDuplicates2(Node head){
        
    }

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }

    public static Node node;// list head
    public static final void main(String[] args){
        Scanner scanner = new Scanner("1 3 2 4 5 2 3 4 8 9 10 1");
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
        
        CC21.removeDuplicates(head);
        CC21.printNodes(head);
        return;
    }
}

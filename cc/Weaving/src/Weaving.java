import java.util.Scanner;




//weaving linked list
//

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



public class Weaving{
 


    public static void weave(Node head){


        //get the mid point..
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        boolean odd = false;
        if(fast != null)//odd number of elements
        {
            slow = slow.next;
            odd = true;
        }
        Node traverser1 = head;
        Node traverser2 = slow;
        while(traverser2 != null){
            System.out.print(traverser1.value);
            Node next1 = traverser1.next;
            Node next2 = traverser2.next;
            traverser1.next = traverser2;
            traverser2.next = next1;
            if(next2 == null && odd == false){
                traverser2.next = null;
            }
            else if(next2 == null && odd == true){
                next1.next= null; 
            }
            traverser1 = next1;
            traverser2 = next2;
            
        }
        return;
    }

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner("1 2 3 4 1 2 3 4 5");
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

        Weaving.printNodes(head);
        Weaving.weave(head);
        System.out.println();
        Weaving.printNodes(head);
        return;
    }
}

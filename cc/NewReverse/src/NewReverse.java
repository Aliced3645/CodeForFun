import java.util.Scanner;

class Node{
    public int value;
    public Node next;

    public Node(int _value){
        this.value = _value;
    }

    public Node(Node prev, int _value){
        this.value = _value;
        if(prev == null)
            return;
        prev.next = this;
        next = null;
        return;
    }
}


public class NewReverse{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }


    //reverse and create a new linked list which is the reverse version
    public static Node newReverse(Node head){
        if(head == null)
            return null;
        else if(head.next == null){
            Node newHead = new Node(head.value);
            return newHead;
        }
        else{
            Node traverser = head.next;
            //create the new head of the new linked list
            Node newPre = new Node(head.value);
            Node newHead = newPre;
            Node newNext = null;
            while(traverser != null){
                newNext = new Node(traverser.value);
                newNext.next = newPre;
                newPre = newNext;
                traverser = traverser.next;
            }
            return newNext;
        }
    }


    public static Node merge(Node head1, Node head2){
        Node node1 = head1;
        Node node2 = head2;
        Node traverser = null;
        Node head = null;
        while(node1 != null && node2 != null){
            Node next1 = node1.next;
            Node next2 = node2.next;
            if(head == null){
                head = traverser = node1;
                traverser.next = node2;
                traverser = traverser.next;
            }
            else{
                traverser.next = node1;
                traverser = traverser.next;
                traverser.next = node2;
                traverser = traverser.next;
                
            }
            node1= next1;
            node2 = next2;
        }


        if(node1 != null){
            traverser.next = node1;
        }
        else if(node2 != null){
            traverser.next = node2;
        }

        return head;       
    }


    public static final void main(String[] args){
        Scanner scanner = new Scanner("1 2 3");
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
        
        NewReverse.printNodes(head);
        Node newHead = NewReverse.newReverse(head);
        System.out.println();
        NewReverse.printNodes(newHead);
        System.out.println();
        Node newnewHead = NewReverse.merge(head,newHead);
        NewReverse.printNodes(newnewHead);
       
        return;
    }

  
}

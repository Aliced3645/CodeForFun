import java.util.Scanner;

//partition a linked list around value x
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

public class CC24{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }
    
    public static Node partition(Node head, int pivot){
        //keep two link lists and at last merge to one
        if(head == null)
            return null;

        Node bigger = null;
        Node smaller = null;
        Node biggerLast = null;
        Node smallerLast = null;
        
        Node pre = head;
        Node later = head.next;
        while(later != null){
            if(pre.value <= pivot){
                if(smaller == null){
                    smaller = pre;
                    smallerLast = pre;
                }
                else{
                    smallerLast.next = pre;
                    smallerLast = pre;
                }
                pre = later;
                later = later.next;
            }
            else if(pre.value > pivot){
                if(bigger == null){
                    bigger = pre;
                    biggerLast = pre;
                }
                else{
                    biggerLast.next = pre;
                    biggerLast = pre;
                }

                pre = later;
                later = later.next;
            }
        }
        
        //look at the last element!!!
        if(pre.value <= pivot){
                if(smaller == null){
                    smaller = pre;
                    smallerLast = pre;
                }
                else{
                    smallerLast.next = pre;
                    smallerLast = pre;
                }
                }
            else if(pre.value > pivot){
                if(bigger == null){
                    bigger = pre;
                    biggerLast = pre;
                }
                else{
                    biggerLast.next = pre;
                    biggerLast = pre;
                }
            }

        smallerLast.next = bigger;
        biggerLast.next = null;
        
        return smaller;
    }


    public static final void main(String[] args){
        Scanner scanner = new Scanner("1 3 2 4 5 2 3 4 8 9 10 1 2");
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
               
        CC24.printNodes(CC24.partition(head,5));
        return;
    }

}

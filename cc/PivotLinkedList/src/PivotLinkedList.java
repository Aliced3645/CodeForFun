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

public class PivotLinkedList{

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
        

        Node traverser = head;
        while(traverser != null){
            if(traverser.value < pivot){
                if(smaller == null){
                    smaller = traverser;
                    smallerLast = traverser;
                }
                else{
                    smallerLast.next = traverser;
                    smallerLast = traverser;
                }
            }
            else{
                if(bigger == null){
                    bigger = traverser;
                    biggerLast = traverser;
                }
                else{
                    biggerLast.next = traverser;
                    biggerLast = traverser;
                }
            }
            traverser = traverser.next;
        }


        biggerLast.next = null;
        smallerLast.next = bigger;
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
               
        PivotLinkedList.printNodes(PivotLinkedList.partition(head,5));
        return;
    }

}

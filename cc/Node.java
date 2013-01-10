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

public class NodeProblem{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }

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

        NodeProblem.printNodes(head);
        return;
    }

}

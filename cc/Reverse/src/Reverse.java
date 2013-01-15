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

public class Reverse{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }


    public static Node reverse(Node head){
        if(head == null || head.next == null)
            return head;
        Node pre = head;
        Node follow = head.next;
        head.next = null;
        while(follow != null){
            Node temp = follow.next;
            follow.next = pre;
            pre = follow;
            follow = temp;
        }
        return pre;
    }
    public static final void main(String[] args){
        Scanner scanner = new Scanner("1 2 3 4 5 6 7 8 9 0");
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

        Reverse.printNodes(head);
        Node headR = Reverse.reverse(head);
        System.out.println();
        Reverse.printNodes(headR);
        return;
    }

}

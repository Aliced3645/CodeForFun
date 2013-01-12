import java.util.Scanner;

//find kth to the last element is the linked list
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

public class CC22{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
    }
    

    public static int getKthToLast(int k, Node head){
        if(k < 0 )
            return -1;
        Node pre = head;
        Node later = head;
        for(int i = 0; i < k; i ++){
            if(later == null)
                return -1;
            later = later . next;
        }
        while(later. next != null){
            later = later. next;
            pre = pre.next;
        }

        return pre.value;    
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

        CC22.printNodes(head);
        System.out.println();
        System.out.println(CC22.getKthToLast(5,head));
        return;
    }

}

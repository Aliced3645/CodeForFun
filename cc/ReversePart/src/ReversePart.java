import java.util.HashSet;
import java.util.LinkedHashSet;
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

public class ReversePart{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
        System.out.println();
    }
    
    public static Node reversePart(Node head, int part){
        if(head == null || head.next == null)
            return head;

        int count = 0;
        Node pre = head;
        Node later = head.next;
        Node next = null;

        while(count < part - 1 && later != null){
            next = later.next;
            later.next = pre;
            pre = later;
            later = next;

            count ++;
        }

        head.next = reversePart(next, part);
        return pre;

    }

    public static final void main(String[] args){

        Scanner scanner = new Scanner("1 2 3 4 5 6 7 8 9 10 11");
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
        Node newHead = ReversePart.reversePart(head,2);
        ReversePart.printNodes(newHead);
        
        //test hashmap key order
        HashSet<Character> set = new LinkedHashSet<Character>();
        String s = "ccchhhjksalasdfnqhh";
        for(int i = 0; i < s.length(); i ++){
            set.add(s.charAt(i));      
        }
        
        for(Character c:set){
            System.out.print(c + " ");
        }
        
        return;
    }

}

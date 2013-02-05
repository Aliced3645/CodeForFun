import java.util.Scanner;
import java.util.Stack;



//Check if a linked list is a palindrome
//

class Node{
    public char value;
    public Node next;
    public Node(Node prev, char _value){
        this.value = _value;
        if(prev == null)
            return;
        prev.next = this;
        next = null;
        return;
    }
}

public class CC27{

    public static void printNodes(Node head) {
        Node traverser = head;
        while(traverser != null){
            System.out.print((char)traverser.value + " ");      
            traverser = traverser.next;
        }
        System.out.println();
    }

    public static Node recursiveDecide(Node head, int length){
        if(length == 1)
            return head.next;
        if(length == 2){
            return head.value == head.next.value? head.next.next : null;
        }

        Node returned = recursiveDecide(head.next, length - 2);
        if(returned == null || returned.value != head.value)
            return null;
        else
            return returned.next;
        
    }

    public static boolean decidePalindrome(Node head, int length){
        if(length == 1)
            return true;
        if(length == 2){
            if (head.value == head.next.value)
                return true;
            else
                return false;
        }

        Node returned = recursiveDecide(head.next, length - 2);
        if(returned == null || returned.value != head.value)
            return false;
        else
            return true;
    }
    
    public static boolean decidePalindromeWithStack(Node head){
        Stack<Character> stack = new Stack<Character>(); 
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            stack.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }
        //It is in the middle. to see whether to skip
        if(fast != null)
            slow = slow.next;
        
        while(slow != null){
            char c = stack.pop();
            if(c != slow.value)
                return false;
            slow = slow .next;
        }

        return true;
    }

    public static final void main(String[] args){
        Scanner scanner = new Scanner("a e c d e d c b a");
        Node head = null;
        Node prev = null;
        while(scanner.hasNext()){
            if(prev == null){
                String a = scanner.next();
                char k = a.toCharArray()[0];
                head = new Node(prev, k);
                prev = head;
            }

            else{
                String a = scanner.next();
                char k = a.toCharArray()[0];
                prev = new Node(prev, k);
            }
        }
        
        int length = 0;
        Node traverser = head;
        while(traverser != null){
            length ++;
            traverser = traverser.next;
        }
        
        CC27.printNodes(head);
        System.out.println(CC27.decidePalindrome(head, length));
        System.out.println(CC27.decidePalindromeWithStack(head));

        return;
    }
}

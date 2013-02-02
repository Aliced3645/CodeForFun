import java.util.Scanner;


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



public class MergeSortLinkedList{
    

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
        System.out.println();
    }


    public static Node mergeSort(Node head){
        //get the mid point
        if(head == null)
            return null;
        else if(head.next == null)
            return head;
        
        //get the mid
        Node slow = head;
        Node slow_previous = null;
        Node fast = head;
        //point!!
        while(fast != null && fast.next != null){
            slow_previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //now slow points to the mid point
        //get two sub linked lists

        slow_previous.next = null;
        Node newHead1 = mergeSort(head);
        Node newHead2 = mergeSort(slow);
        
        //do merge here
        Node node1 = newHead1;
        Node node2 = newHead2;
        
        Node newHead = null;
        Node toAdd = null;
        Node traverser = null;

        while(node1 != null && node2 != null){
            if(node1.value > node2.value){
                toAdd = node2;
                node2 = node2.next;
            }
            else{
                toAdd = node1;
                node1 = node1.next;
            }
            if(newHead == null){
                newHead = toAdd;
                traverser = toAdd;
            }
            else{
                traverser.next = toAdd;
                traverser = toAdd;
            }
            
        }

        //now append the remaining part
        if(node1 != null){
            traverser.next = node1;
        }
        else if(node2 != null){
            traverser.next = node2;
        }
        
        
        return newHead;
    }

    public static void main(String[] args){
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
        
        MergeSortLinkedList.printNodes(head);
        Node newHead = MergeSortLinkedList.mergeSort(head);
        MergeSortLinkedList.printNodes(newHead);
        return ;
    }
}

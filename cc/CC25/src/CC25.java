import java.util.Scanner;

//partition a linked list around value x
//
class Node{
    public int value;
    public Node next;
    public Node(int _value){
        value = _value;
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


class Result{
    public Node node;
    public int borrow;
    public Result(){
        node = null;
        borrow = 0;
    }
    public Result(int _value, int _borrow){
        node = new Node(_value);
        borrow = _borrow;
    }
}


public class CC25{

    public static void printNodes(Node head){
        Node traverser = head;
        while(traverser != null){
            System.out.print(traverser.value + " ");      
            traverser = traverser.next;
        }
        System.out.println();
    }
    

    public static Node constructLinkedList(String numbers){
        Scanner scanner1 = new Scanner(numbers);
        Node head = null;
        Node prev = null;
        while(scanner1.hasNext()){
            if(prev == null){
                head = new Node(prev, scanner1.nextInt());
                prev = head;
            }
            else
                prev = new Node(prev, scanner1.nextInt());
        }
        return head;
    }
    


    public static Result recursiveAdd(Node digit1, Node digit2){
        
        if(digit1 == null && digit2 == null){
            //termination condition
            Result res = new Result();
            res.borrow = 0;
            res.node = null;
            return res;
        }

        else{
            //recursive manipulation
            Result nextDigit = recursiveAdd(digit1.next, digit2.next);
            int addAnswer = digit1.value + digit2.value + nextDigit.borrow;
            int toBorrow = addAnswer >= 10? 1 : 0;
            Result toReturn = new Result(addAnswer >= 10? addAnswer - 10 : addAnswer, toBorrow);
            toReturn.node.next = nextDigit.node;
            return toReturn;
        }
        
    }


    public static Node add(Node num1, Node num2){
        //pad 0s first
        //get num1 size
        if(num1 == null || num2 == null)
            return null;
        
        Node traverser = num1;
        int num1Length = 0;
        while(traverser != null){
            traverser = traverser.next;
            num1Length ++ ;
        }
        int num2Length = 0;
        traverser = num2;
        while(traverser != null){
            traverser = traverser.next;
            num2Length ++;
        }

        //decide how many zeros to append
        int zeros = (num1Length - num2Length);
        Node head = null;
        Node prev = null;

        
        for(int i = 0; i < Math.abs(zeros); i ++){
            if(prev == null){
                head = new Node(prev, 0);
                prev = head;
            }
            else
                prev = new Node(prev, 0);
        }

        //append the sublist
        //if zero == 1, don't need to operate
        if(zeros > 0){
            //the new head is given to num2
            prev.next = num2;
            num2 = head;
        }
        else if(zeros < 0){
            //the new head is given to num1
            prev.next = num1;
            num1 = head;
        }


        //do recursive computation
        Result res = recursiveAdd(num1,num2);
        //check whether there is a carry
        if(res.borrow == 1){
            Node newHead = new Node(1);
            newHead.next = res.node;
            return newHead;
        }
        else
            return res.node;

    }


    public static final void main(String[] args){
        
        Node numberList1 = CC25.constructLinkedList("6 1 7");
        CC25.printNodes(numberList1);
    
        Node numberList2 = CC25.constructLinkedList("9 5");
        CC25.printNodes(numberList2);
        
        Node sum = CC25.add(numberList1, numberList2);
        CC25.printNodes(sum);

        return;
    }

}

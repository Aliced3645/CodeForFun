import java.util.Stack;




//Solution Logic Chain:
//1. Use stack to record all digits, and also count the length when pushing
//2. padding the stack to the length of 
//4. when three is printed, print "thousand, million, billiion" and comma

public class CC177{
    
    //the "atom" fucntion, just print number
    public static String printOneInteger(Integer i){
        switch(i){
            case 1:
                return "one ";
            case 2: 
                return "two ";
            case 3:
                return "three ";
            case 4:
                return "four ";
            case 5:
                return "five ";
            case 6:
                return "six ";
            case 7:
                return "seven ";
            case 8:
                return "eight ";
            case 9:
                return "nine ";

            default:
                return "";
        }
    }


    public static String printLastTwoIntegersInThreeIntegerGroup(Integer i1, Integer i2){
        String toReturn = "";
        switch(i1){
                case 1:
                    switch(i2){
                        case 0:
                            return "ten ";
                        case 1:
                            return "eleven ";
                        case 2:
                            return "twelve ";
                        case 3:
                            return "thirteen ";
                        case 4:
                            return "fourteen ";
                        case 5:
                            return "fifteen ";
                        case 6:
                            return "sixteen ";
                        case 7:
                            return "seventeen ";
                        case 8:
                            return "eighteen ";
                        case 9:
                            return "nighteen ";
                    }
                case 2:
                    toReturn += "twenty ";
                    break;
                case 3:
                    toReturn += "thirty ";
                    break;
                case 4:
                    toReturn +=  "forty ";
                    break;
                case 5:
                    toReturn += "fifty ";
                    break;
                case 6:
                    toReturn += "sixty ";
                    break;
                case 7:
                    toReturn += "seventy ";
                    break;
                case 8:
                    toReturn += "eighty ";
                    break;
                case 9:
                    toReturn += "nighty ";
                    break;
                
            }

        toReturn += printOneInteger(i2);
        return toReturn;
    }


    public static String printThreeIntegerGroup(Integer i1, Integer i2, Integer i3){
        String toReturn = "";
        toReturn += printOneInteger(i1);
        if(i1 != 0){
            toReturn += "hundred ";
            if(i2 == 0 && i3 == 0)
                return toReturn;
            toReturn += "and ";
            toReturn += printLastTwoIntegersInThreeIntegerGroup(i2,i3);
        }
        else
            toReturn += printLastTwoIntegersInThreeIntegerGroup(i2,i3);
        
        return toReturn;
    }

    
    public static String printComma(int i){
        if(i < 0 || i >= 3)
            return "";
        if(i == 0){
            return "thousand, " ;
        }
        else if(i == 1){
            return "million, ";
        }
        else if(i == 2){
            return "billion, ";
        }
        return "";
    }

    //return the digit length of the number
    
    public static int pushToStack(int number, Stack<Integer> stack){
        
        int tempNumber = number;
        int digitCount = 0;
        while(tempNumber != 0){
            int tail = tempNumber % 10;
            tempNumber /= 10;
            stack.push(tail);
            digitCount ++;
        }

        //pad the stack with zeros
        int toPad = 3 - digitCount % 3;
        digitCount += toPad;
        if(toPad != 3){
            while(toPad != 0){
                stack.push(0);
                toPad --;
            }
        }
        
        return digitCount;
    }

    public static String printNumber(int number){
        String toReturn = "";
        if(number < 0){
            toReturn += "minus ";
            number *= -1;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int digitsPadded = pushToStack(number, stack);


        //do the loop and construct the string
        int commaCount = digitsPadded / 3 - 2;
        int i1;
        int i2;
        int i3;

        while( !stack.isEmpty()){
            i1 = stack.pop();
            i2 = stack.pop();
            i3 = stack.pop();
            toReturn += printThreeIntegerGroup(i1,i2,i3);
            if(commaCount >= 0){
                toReturn += printComma(commaCount);
                commaCount -- ;
            }
        }
    
        return toReturn;
    }

    public static final void main(String[] args){
        
        System.out.println(CC177.printNumber(1234));
        return;
    }
}

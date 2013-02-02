

public class ReverseSentence{
    
    public static String reverseSentence(String sentenceString){
        char[] rev = new char[sentenceString.length()];           
        char[] sentence = sentenceString.toCharArray();
        
        //traverser for the back
        int wordEnd = sentence.length -1;
        int revPointer = 0;
        for(int i = sentence.length - 1; i >= 0; i --){
            if(sentence[i] == ' '){
                int traverser = i + 1;
                while(traverser <= wordEnd){
                    rev[ revPointer++ ] = sentence[ traverser++ ];
                }
                wordEnd = i - 1;
                rev[revPointer++] = ' ';
            }
        }
        //copy the first word!
        int traverser = 0;
        while(traverser <= wordEnd){
            rev[revPointer ++] = sentence[traverser ++];           
        }
        return new String(rev);
    }

    public static void main(String[] args){
        String sentence = "We will we will rock you";
        System.out.println(ReverseSentence.reverseSentence(sentence));
        return;
    }
}

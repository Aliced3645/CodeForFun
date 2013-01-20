import java.util.LinkedList;



//a linkedin interview problem


public class FindDepth{
    
    public static LinkedList<Integer> detectLeafs(String str){
        LinkedList<Integer> ll = new LinkedList<Integer>();
        int index = 0;
        while( (index = str.indexOf("(00)", index)) != -1){
            ll.add(index);
            index ++;
        }
        return ll;
    }

    //eliminate a leaf root
    public static String eliminateLeaf(String string, int startIndex){
        
        String newString = string.substring(0, startIndex);
        newString = newString + "0";
        if(startIndex + 4 < string.length())
            newString = newString + string.substring(startIndex+ 4, string.length());
        return newString;
        
    }


    public static int findDepth(String str){
        
        //to elimination recursively and at the end check whether only "0" left
        int levelCount = -1;
        LinkedList<Integer> leafIndices = detectLeafs(str);
        while( !leafIndices.isEmpty()){
            for(int i : leafIndices){
                if(i != leafIndices.getFirst())
                    str = eliminateLeaf(str, i - 3);
                else
                    str = eliminateLeaf(str,i);
                                    
            }
            levelCount ++;
            System.out.println(str);
            leafIndices = null; // baggage redeem
            leafIndices = detectLeafs(str);
        }
        return levelCount;
    }
    
    public static void main(String[] args){
        


        //System.out.println(FindDepth.detectLeafs("((00)0)"));
        //String s =  FindDepth.eliminateLeaf("((00)(00))", 1);
        //System.out.println(s);
        //test cases
        
        //    System.out.println(FindDepth.findDepth("(00)"));
        //    System.out.println(FindDepth.findDepth("((00)0)"));
            System.out.println(FindDepth.findDepth("((00)(00))"));
            System.out.println(FindDepth.findDepth("((00)(0(00)))"));
            System.out.println(FindDepth.findDepth("((00)(0(0(00))))"));
        //    System.out.println(FindDepth.findDepth("x"));
        //    System.out.println(FindDepth.findDepth("(0)"));
        
        
        return;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

//find a common sequence of URLS visited by all users
//I think it is a N-D common sequence problem


//the record class
//
class Record{
    public String name;
    public String url;
}

public class FindCommonURLSequences{
    
    
    public static HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
    

    public static ArrayList<String> findCommonURLsForTwo(ArrayList<String> list1, ArrayList<String> list2){
        
        //Dynamic programming
        ArrayList<String> table[][] = new ArrayList[list1.size() + 1][list2.size() + 1];
        //initialize all of them
        for(int i = 0; i < table.length; i ++)
            for(int j = 0; j < table[0].length; j ++)
                table[i][j] = new ArrayList<String>();
        
        for(int i = 1; i < table.length; i ++){
            for(int j = 1; j < table[0].length; j ++){
                String string1 = list1.get(i-1);
                String string2 = list2.get(j-1);
                if(string1 == string2){
                    table[i][j] = (ArrayList<String>)table[i-1][j-1].clone();
                    table[i][j].add(string1);
                }
                else{
                    //get the longer substring
                    int length1 = table[i-1][j].size();
                    int length2 = table[i][j-1].size();
                    table[i][j] = length1 >= length2? (ArrayList<String>)table[i-1][j].clone() : (ArrayList<String>)table[i][j-1].clone();
                }
            }
        }
        return table[list1.size()][list2.size()];
    }

    public static ArrayList<String> findCommonURLs(Record[] records){
        //cast to hashmap
        for(Record record : records){
            if(hash.containsKey(record.name)){
                ArrayList<String> list = hash.get(record.name);        
                list.add(record.url);
            }
            else{
                hash.put(record.name, new ArrayList<String>());
            }
        }

        //iterate all compare two by two..
        ArrayList<String> urls = null;
        for(Entry<String, ArrayList<String>> e : hash.entrySet()){
            if(urls == null)
                urls = e.getValue();
            else{
                urls = findCommonURLsForTwo(urls, e.getValue());
            }
        }
        return urls;

    }

    public static void main(String[] args){
        return;  
    }
}

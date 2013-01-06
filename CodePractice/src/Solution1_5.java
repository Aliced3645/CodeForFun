import java.util.ArrayList;
import java.util.List;

class CharCount{
	public CharCount(){}
	public char character;
	public int count;
}

public class Solution1_5 {
	
	//my solution..
	public static char[] comptressString_1(char[] input){
		String temp = null;
		List<CharCount> record = new ArrayList<CharCount>(0);
		
		for(int i = 0; i < input.length; i ++){
			CharCount cc = new CharCount();
			cc.character = input[i];
			cc.count = 1;
			int j = i + 1;
			if( j != input.length){
				for(; j < input.length; j ++){
					if( input[j] == input[j-1]){
						cc.count ++ ;
					}
					else break;
				}		
				i = j - 1;
			}
			record.add(cc);
		}		
	        	
		//reconstruct the new String
		String compressed = new String();
		for(CharCount c : record){
			compressed += c.character;
			compressed += Integer.toString(c.count);
		}
		if(compressed.length() >= input.length)
			return input;
		else
			return compressed.toCharArray();
	}
	
	
	public static String comptressString_2(String input){
		int count = 1;
		int first = 0;
		StringBuffer compressed = new StringBuffer(); // will be quicker
		
		for(int i = first + 1; i < input.length(); i ++){
			if(input.charAt(i) == input.charAt(first)){
				count ++;
			}
			else{
				compressed.append(input.charAt(first));
				compressed.append(count);
				first = i;
				count = 1;
			}
		}
		
		//for the end...
		compressed.append(input.charAt(first));
		compressed.append(count);
		
		
		String res = compressed.toString();
		
		if(res.length() >= input.length())
			return input;
		else
			return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "aabcccccaaa";
		String b = "aaaaaaa";
		String result = Solution1_5.comptressString_2(b);
		System.out.println(result);
		
	}

}

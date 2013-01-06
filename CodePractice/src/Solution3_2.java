import java.util.ArrayList;



class MinStack{
	public final int SIZE= 10;
	public int[] data = new int[SIZE];
}

public class Solution3_2 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		list.remove(0);
		System.out.println(list.get(0));
		
		String a = "adfadf";
		System.out.println(a.charAt(1));
	}

}

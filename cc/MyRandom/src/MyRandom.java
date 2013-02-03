import java.util.Random;




public class MyRandom{
    

    public static void swap(int[] array, int pos1, int pos2){
            int temp = array[pos1];
            array[pos1] = array[pos2];
            array[pos2] = temp;
            return;
    }
    
    public static void shuffleArray(int[] array){
        Random random = new Random();
        for(int i = array.length; i > 0; i --){
            int extract = random.nextInt(i);
            swap(array, i-1, extract);
        }
    }

    public static void main(String[] args){
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        MyRandom.shuffleArray(array);
        for(int i = 0; i < array.length; i ++){
            System.out.print(array[i] + " ");
        }
        return;
    }
}

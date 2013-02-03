import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;




//a thread doing counting work
class CountingThread implements Runnable{
    public static int max = 1000;
    public static int current = 0;
    public int localCurrent = 0;
    public static Lock lock = new ReentrantLock();
    public void run(){
        System.out.println(localCurrent);
        while(current < max){
            synchronized(this){
                lock.lock();
                current ++;
                localCurrent = current;
                lock.unlock();
          }
            System.out.println(localCurrent);
        }
    }
   
}


class MyThread implements Runnable{
    public void run(){
        for(int i = 0; i < 1000000; i ++)
            System.out.println("hello " + i);
    }
}

public class MultiThreads {

    public static void main(String[] args) throws Exception{
        
        /*
        CountingThread[] ct = new CountingThread[4];
        Thread[] thread = new Thread[4];
        for(int i = 0; i < ct.length; i ++){
            thread[i] = new Thread(ct[i]);
            thread[i].start();
        }
        for(int i = 0; i < ct.length; i ++){
            thread[i].join();
        }
        */

        Thread t = new Thread(new MyThread());
        t.start();
        return;
    }
}


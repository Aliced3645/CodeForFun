import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



//the queue is like a book
//provides interface read()/write()

public class BlockingQueue2<T>{
    
    private final int volumn = 10;
    private LinkedList<T> queue = new LinkedList<T>();
    private Semaphore space = new Semaphore(volumn);
    private Semaphore occupies = new Semaphore(0);
    private Lock queueLock = new ReentrantLock();

    public synchronized T read() throws Exception{
        //first trying to lock the queue
        queueLock.lock();
        //now acquires the control of the queue

        //see whether the semaphore is 0
        //THIS MAKES THE QUEUE NOT BLOCKING!!!
        //SO THE CLASS NAME MIGHT BE MISLEADING
        if(!occupies.tryAcquire()){
            queueLock.unlock();
            return null;
        }
        //now acquired
        T object = queue.poll();
        space.release();
        occupies.acquire();
        
        queueLock.unlock();
        return object;
    }

    public synchronized void write(T object) throws Exception{
        queueLock.lock();
        if(!space.tryAcquire()){
            queueLock.unlock();
            return;
        }
        queue.offer(object);
        space.acquire();
        occupies.release();
        queueLock.unlock();
        return;
    }

    public static void main(String[] args){
        return;
    }
}

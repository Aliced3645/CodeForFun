import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;





public class BlockQueue<T>{
    
    //or use semaphore
    
    private Semaphore availableSpace = new Semaphore(10);
    private Semaphore usedSpace = new Semaphore(0);
    
    private LinkedList<T> queueBuffer;
    private int queueSize;
    private int currentSize;
    private Lock lock = new ReentrantLock();
    public BlockQueue(int queueSize){
        queueBuffer = new LinkedList<T>();
        this. queueSize = queueSize;
        currentSize = 0;
    }
    
    public boolean tryEnqueue(T item) throws Exception{
        if(currentSize == queueSize){
            return false;
        }
        enqueue(item);
        return true;
    }

    public void enqueue(T item) throws Exception{
        lock.lock();
        if(currentSize == queueSize){
            lock.unlock();
            this.wait();
        }
        

        queueBuffer.addLast(item); 
        currentSize ++;
        lock.unlock();
        this.notifyAll();
    }

    public T tryDequeue() throws Exception{
        if(currentSize == 0)
            return null;
        return dequeue();
    }

    public T dequeue() throws Exception{
        lock.lock();
        if(currentSize == 0){
            lock.unlock();
            this.wait();
        }
    

        T toReturn = queueBuffer.remove(0);
        currentSize --;
        lock.unlock();
        this.notifyAll();
        return toReturn;
    }

}

import java.util.Arrays;
import java.util.concurrent.locks.*;

public class CircularQueue<E> {
    private int currentSize;
    private int maxSize;
    private E[] buffer;
    private int firstElement;
    private int lastElement;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        buffer = (E[]) new Object[this.maxSize];
        currentSize = 0;
        firstElement = -1;
        lastElement = -1;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();

    }

    public  void setElement(E item) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                System.out.println("Maximum capacity reached");
                notEmpty.await();//
            }
            lastElement = (lastElement + 1) % buffer.length;
            buffer[lastElement] = item;
            currentSize++;
            notFull.signalAll();
            if (firstElement == -1) {
                firstElement = lastElement;
            }
        }finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        return (currentSize == buffer.length);
    }

    public E getElement() throws InterruptedException {
        lock.lock();
        try {
            E DequedElement = null;
            while (isEmpty()) {
                System.out.println("Minimal capacity reached");
                notFull.await();
            }

            DequedElement = buffer[firstElement];
            buffer[firstElement] = null;
            firstElement = (firstElement + 1) % buffer.length;
            currentSize--;
            notEmpty.signalAll();
            return DequedElement;
        }finally {
            lock.unlock();
        }

    }

    private boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public String toString() { //test method no for using
        return "CircularQueue{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }
}

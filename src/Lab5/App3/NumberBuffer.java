package Lab5.App3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumberBuffer {
    private final Queue<Integer> buffer;
    Lock lock;
    private final Condition bufferFull;
    private final Condition bufferEmpty;

    public NumberBuffer() {
        this.buffer = new ConcurrentLinkedQueue<>();
        this.lock = new ReentrantLock();
        this.bufferFull = lock.newCondition();
        this.bufferEmpty = lock.newCondition();
    }

    public void addNumber(int number) {
        lock.lock();
        try {
            while (buffer.size() == 100) {
                bufferFull.await();
            }
            buffer.add(number);
            bufferEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int removeNumber() {
        lock.lock();
        int number = 0;
        try {
            while (buffer.size() == 0) {
                bufferEmpty.await();
            }
            number = buffer.remove();
            bufferFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return number;
    }
}

package com.github.gelald.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WuYingBin
 * date: 2023/7/4
 */
public class PrintTest2 {
    public static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread a = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 50; i++) {
                    System.out.println("A\t" + i);
                    condition1.signal();
                    condition2.await();
                }
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread b = new Thread(() -> {
            try {
                lock.lock();
                a.start();
                for (int i = 0; i < 50; i++) {
                    condition1.await();
                    System.out.println("B\t" + i);
                    condition2.signal();
                }
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        b.start();
    }
}

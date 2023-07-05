package com.github.gelald.print;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WuYingBin
 * date: 2023/7/5
 */
public class PrintTest4 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("A\t" + i);
                LockSupport.unpark(threads[1]);
                LockSupport.park();
            }
        });
        threads[1] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                LockSupport.park();
                System.out.println("B\t" + i);
                LockSupport.unpark(threads[0]);
            }
        });
        threads[0].start();
        threads[1].start();
    }
}

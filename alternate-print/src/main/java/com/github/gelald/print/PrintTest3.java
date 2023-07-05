package com.github.gelald.print;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WuYingBin
 * date: 2023/7/4
 */
public class PrintTest3 {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    private static final int MAX_COUNT = 100;
    private static final Semaphore SEMAPHORE_A = new Semaphore(1);
    private static final Semaphore SEMAPHORE_B = new Semaphore(0);

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            while (ATOMIC_INTEGER.get() < MAX_COUNT) {
                try {
                    SEMAPHORE_A.acquire();
                    ATOMIC_INTEGER.incrementAndGet();
                    if (ATOMIC_INTEGER.get() > MAX_COUNT) {
                        return;
                    }
                    System.out.println("A\t" + ATOMIC_INTEGER.get());
                    SEMAPHORE_B.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        CompletableFuture.runAsync(() -> {
            while (ATOMIC_INTEGER.get() < MAX_COUNT) {
                try {
                    SEMAPHORE_B.acquire();
                    ATOMIC_INTEGER.incrementAndGet();
                    if (ATOMIC_INTEGER.get() > MAX_COUNT) {
                        return;
                    }
                    System.out.println("B\t" + ATOMIC_INTEGER.get());
                    SEMAPHORE_A.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).join();
    }
}

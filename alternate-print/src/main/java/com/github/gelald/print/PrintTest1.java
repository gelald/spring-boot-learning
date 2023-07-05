package com.github.gelald.print;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WuYingBin
 * date: 2023/7/4
 */
public class PrintTest1 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        AtomicInteger atomicInteger = new AtomicInteger();
        CompletableFuture.runAsync(() -> {
            try {
                while (atomicInteger.incrementAndGet() < 100) {
                    System.out.println("A\t" + atomicInteger.get());
                    cyclicBarrier.await();
                    cyclicBarrier.await();
                }
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture.runAsync(() -> {
            try {
                while (atomicInteger.incrementAndGet() < 100) {
                    cyclicBarrier.await();
                    System.out.println("B\t" + atomicInteger.get());
                    cyclicBarrier.await();
                }
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.currentThread().join();
    }
}

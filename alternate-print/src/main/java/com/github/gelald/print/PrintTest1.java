package com.github.gelald.print;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用两个线程，交替地打印A、B字符各50次
 * 难点1:如何确保第一个打印的字符是A
 * 难点2:如何控制交替打印
 * 难点3:如何控制打印100个字符
 * <p>
 * 扩展点:控制两个服务来交替打印A、B字符(分布式锁)
 *
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

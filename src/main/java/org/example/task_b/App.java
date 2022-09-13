package org.example.task_b;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
    // warehouse -> Ivanov -> ground -> Petrov -> truck -> Nechiporchuk
    static final Queue<Goods> warehouse = new LinkedList<>();
    static final Queue<Goods> ground = new LinkedList<>();
    static final Queue<Goods> truck = new LinkedList<>();
    static final AtomicBoolean warehouseDone = new AtomicBoolean(false);
    static final AtomicBoolean groundDone = new AtomicBoolean(false);
    static int costSummary = 0;

    public static void main(String[] args) throws InterruptedException {
        warehouse.addAll(
            List.of(
                new Goods[]{
                        new Goods("Laptop", 5000F),
                        new Goods("Teapot", 250F),
                        new Goods("Cow", 17000F),
                        new Goods("Cookies", 50F),
                        new Goods("Table", 1300F)
                }
            )
        );

        Ivanov ivanov = new Ivanov();
        Petrov petrov = new Petrov();
        Nechiporchuk nechiporchuk = new Nechiporchuk();
        ivanov.start();
        petrov.start();
        nechiporchuk.start();
        nechiporchuk.join();
        System.out.println("Cost summary: " + costSummary);
    }
}

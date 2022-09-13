package org.example.task_b;

public class Nechiporchuk extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            Goods goods;
            synchronized (App.truck) {
                goods = App.truck.poll();
            }
            if (goods == null) {
                if (!App.groundDone.get()) {
                    continue;
                } else {
                    break;
                }
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                break;
            }
            App.costSummary += goods.cost;
            System.out.println("Nechiporchuk: " + goods.name + " valued at " + goods.cost);
        }
        System.out.println("Nechiporchuk: Done");
    }
}

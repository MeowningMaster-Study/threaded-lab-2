package org.example.task_b;

public class Petrov extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            Goods goods;
            synchronized (App.ground) {
                goods = App.ground.poll();
            }
            if (goods == null) {
                if (!App.warehouseDone.get()) {
                    continue;
                } else {
                    App.groundDone.set(true);
                    break;
                }
            }
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                break;
            }
            synchronized (App.truck) {
                App.truck.add(goods);
            }
            System.out.println("Petrov: " + goods.name + " in the truck");
        }
        System.out.println("Petrov: Done");
    }
}

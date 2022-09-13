package org.example.task_b;

public class Ivanov extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            Goods goods = App.warehouse.poll();
            if (goods == null) {
                App.warehouseDone.set(true);
                break;
            }
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                break;
            }
            synchronized (App.ground) {
                App.ground.add(goods);
            }
            System.out.println("Ivanov: " + goods.name + " on the ground");
        }
        System.out.println("Ivanov: Done");
    }
}

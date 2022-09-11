package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class BeesSquad implements Runnable {
    static AtomicBoolean done = new AtomicBoolean(false);
    static Shutdown shutdown;
    private final int fieldIndex;

    public BeesSquad(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    @Override
    public void run() {
        if (done.get()) {
            return;
        }

        int delay = ThreadLocalRandom.current().nextInt(100,  1000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            return;
        }

        if (App.fields.get(fieldIndex)) {
            done.set(true);
            System.out.println("Bear is punished. Field " + fieldIndex);
            shutdown.call();
        } else {
            System.out.println(fieldIndex + " checked in " + delay + "ms");
        }
    }
}

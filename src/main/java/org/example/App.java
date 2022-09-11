package org.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class App
{
    static ArrayList<Boolean> fields;

    public static void main( String[] args )
    {
        int fieldsCount = ThreadLocalRandom.current().nextInt(50,  100);
        int bearPosition = ThreadLocalRandom.current().nextInt(1,  fieldsCount);
        System.out.println("Fields count: " + fieldsCount);
        System.out.println("Bear position: " + bearPosition);

        fields = new ArrayList<>(fieldsCount);
        for (int i = 0; i < fieldsCount; i += 1) {
            fields.add(i == bearPosition);
        }

        int beesSquadsCount = ThreadLocalRandom.current().nextInt(2, 8);
        ExecutorService executor = Executors.newFixedThreadPool(beesSquadsCount);
        BeesSquad.shutdown = executor::shutdownNow;
        for (int i = 0; i < fieldsCount; i += 1) {
            executor.execute(new BeesSquad(i));
        }
    }
}
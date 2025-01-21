import java.util.Random;

class Biker implements Runnable {
    public static int totalDistance = 100;
    private static Biker[] results = new Biker[10];
    private static int finishedCount = 0;

    private int speed = 0;
    public int time = 0;
    private int distance = 0;
    private String name;
    Random a = new Random();

    public Biker(String name) {
        this.name = name;
    }

    void accelerate() {
        int inc = a.nextInt(5) + 1;
        speed += inc;
        System.out.println("Acceleration of " + name + ": " + inc);
    }

    public void run() {
        while (distance < totalDistance) {
            try {
                accelerate();
                time += 1;
                distance += speed;
                Thread.sleep(a.nextInt(1000));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        synchronized (Biker.class) {
            results[finishedCount++] = this;
        }
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public static Biker[] getResults() {
        return results;
    }
}

public class Race {
    public static void main(String[] args) {
        Thread racers[] = new Thread[10];
        for (int i = 9; i > 0; i--) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("GO");

        for (int i = 0; i < 10; i++) {
            Biker biker = new Biker("Racer - " + (i + 1));
            racers[i] = new Thread(biker);
            racers[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                racers[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Biker[] results = Biker.getResults();

        for (int i = 0; i < results.length - 1; i++) {
            for (int j = 0; j < results.length - i - 1; j++) {
                if (results[j].getTime() > results[j + 1].getTime()) {
                    Biker temp = results[j];
                    results[j] = results[j + 1];
                    results[j + 1] = temp;
                }
            }
        }

        System.out.println("\nFinal Rankings:");
        for (int i = 0; i < results.length; i++) {
            System.out.println((i + 1) + ". " + results[i].getName() + " - Time: " + results[i].getTime() + " s");
        }
    }
}

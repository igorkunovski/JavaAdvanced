import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher implements Runnable{

    private static final AtomicInteger rounds = new AtomicInteger(1);
    private final int name;
    private Boolean isSleeping;
    private int eatingCounter;
    private static final int maxEatingCounter = 3;


    Philosopher(int name){
        this.name = name;
        this.isSleeping = new Random().nextBoolean();
        this.eatingCounter = 0;
    }

    public Boolean isSleeping() {
        return isSleeping;
    }

    public int getName() {
        return name;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    @Override
    public void run() {

        while (true) {

            try {

                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (rounds) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (isSleeping()) {
//                    System.out.println("Round: " + rounds.get());
                    rounds.set(rounds.get()+1);
                    System.out.println(this + " is meditating");
                    this.setSleeping(false);

                } else {
//                    System.out.println("Round: " + rounds.get());
                    rounds.set(rounds.get()+1);
                    System.out.println(this + " is eating");
                    eatingCounter++;
                    if (eatingCounter == maxEatingCounter) {
                        System.out.println(this + " has eaten 3 times and now leaving");
                        break;
                    } else {
                        System.out.println(this + " has eaten " + eatingCounter + " times");
                        this.setSleeping(true);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Philosopher nr. " + getName();
    }
}

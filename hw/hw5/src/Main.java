public class Main {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 5; i++){
            Thread philosopher = new Thread(new Philosopher(i));
            philosopher.start();
        }
    }
}
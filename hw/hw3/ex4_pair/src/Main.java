import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Pair<Integer, String> pair1 = new Pair<>(10, "ten");
        Pair<String, Integer> pair2 = new Pair<>("one", 1);
        Pair<Integer, ArrayList<Integer>> pair3 = new Pair<>(1, new ArrayList<>(Arrays.asList(10, 20, 30)));

        System.out.println(pair1.getFirst());
        System.out.println(pair2.getFirst());
        System.out.println(pair1);
        System.out.println(pair2);

        System.out.println(pair3);
        pair3.setValue(new ArrayList<>(Arrays.asList(200, 400, 600)));
        System.out.println(pair3.getSecond());
    }
}

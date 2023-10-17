public class Main {

    public static void main(String[] args) {
        System.out.println(Comparator.compareArrays(new Integer[]{1,2,3,4}, new Integer[]{1,2,3,4}));
        System.out.println(Comparator.compareArrays(new String[]{"one","two"}, new Integer[]{1,2}));
        System.out.println(Comparator.compareArrays(new String[]{"one","two"}, new String[]{"one","two"}));
        System.out.println(Comparator.compareArrays(new String[]{"one","two"}, new String[]{"one","two", "three"}));

    }
}
public class Main {
    public static void main(String[] args) {

        GenericArray<Integer> arrayInteger = new GenericArray<>();

        arrayInteger.info(arrayInteger.getArr());
        arrayInteger.add(27647);
        arrayInteger.add(700);
        arrayInteger.add(32000);
        System.out.println("--------------- after add-----------");
        arrayInteger.info(arrayInteger.getArr());
        arrayInteger.remove(32000);
        System.out.println("--------------- after remove-----------");
        arrayInteger.info(arrayInteger.getArr());


        GenericArray<String> arrayString = new GenericArray<>();

        arrayString.info(arrayString.getArr());
        arrayString.add("one");
        arrayString.add("two");
        arrayString.add("three");
        System.out.println("--------------- after add-----------");
        arrayString.info(arrayString.getArr());
        arrayString.remove("two");
        System.out.println("--------------- after remove-----------");
        arrayString.info(arrayString.getArr());
    }
}
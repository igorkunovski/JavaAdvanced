
public class Main {
    public static void main(String[] args) {


        Catalog catalog = new Catalog();

        catalog.addPerson(new Person("46456456", "Alex", 10));
        catalog.addPerson(new Person("9575789", "Alex", 12));
        catalog.addPerson(new Person("575756", "Bob", 8));
        catalog.addPerson(new Person("475675647", "Michael", 9));
        catalog.addPerson(new Person("13321", "Ann", 11));
        catalog.addPerson(new Person("980989", "Lena", 15));

        catalog.show();
        System.out.println(catalog.findByName("Алекс"));
        System.out.println(catalog.findByPhone("45757567"));
        System.out.println(catalog.findByStringPart("575"));
        System.out.println(catalog.findExperience(9));

        System.out.println(catalog.findByID(10));
        catalog.fire(1);
        catalog.show();

    }
}


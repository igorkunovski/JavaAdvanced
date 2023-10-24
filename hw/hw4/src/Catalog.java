import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Catalog {

    Set<Person> catalog;

    public Catalog() {
        catalog = new LinkedHashSet<>();
    }

    public void addPerson(Person p){
        this.catalog.add(p);
    }

    public ArrayList<Person> findByName(String name){

        ArrayList<Person> result = new ArrayList<>();

        for (Person p : catalog) {

            if (p.getName().equals(name)) result.add(p);
        }
        return result;
    }

    public ArrayList<Person> findByPhone(String phone){
        ArrayList<Person> result = new ArrayList<>();

        for (Person p : catalog) {
            if (p.getPhone().equals(phone)) result.add(p);
        }
        return result;
    }

    public ArrayList<Person> findByStringPart(String part){
        ArrayList<Person> result = new ArrayList<>();

        for (Person p : catalog) {
            if (p.getName().contains(part) || p.getPhone().contains(part)){
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Person> findExperience(int exp){
        ArrayList<Person> result = new ArrayList<>();

        for (Person p : catalog) {
            if (p.getExperience() == exp) result.add(p);
        }
        return result;
    }

    public void show(){
        catalog.forEach(System.out::println);
    }

    public void fire(int id){
        Person p = findByID(id);
        if (p!=null) p.setStatus(false);
    }

    public Person findByID(int id) {
        if (id > 0 && id <= Person.getIdCounter()) {
            for (Person p : catalog) {
                if (p.getPersonId() == id) return p;
            }
        }
        System.out.println("Invalid ID");
        return null;
    }
}

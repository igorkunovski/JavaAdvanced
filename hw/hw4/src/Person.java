import java.util.Objects;

public class Person{

    private static int idCounter = 0;
    private final int personId;
    private String phone;
    private String name;
    private final int experience;
    boolean status;

    public Person(String phone, String name, int experience) {
        this.personId = ++idCounter;
        this.phone = phone;
        this.name = name;
        this.experience = experience;
        this.status = true;
    }

    public int getPersonId() {
        return personId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Имя: %s, Телефон: %s, Стаж: %d, В штате: %s",
                getPersonId(), getName(), getPhone(), getExperience(), isStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return personId == person.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name);
    }

}

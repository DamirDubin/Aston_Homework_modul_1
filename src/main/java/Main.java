import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Address addr = new Address("Moscow", "Arbat");
        Person person = new Person("Ivan", addr);

        addr.setCity("Kazan");

        Address retrievedAddr = person.getAddress();
        retrievedAddr.setCity("Penza");

        System.out.println(person.getAddress().getCity()); // Moscow

        // Рефлексия
        Class<?> personClass = person.getClass();
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Vanya");

        Field addressField = personClass.getDeclaredField("address");
        addressField.setAccessible(true);
        Address newAddr = new Address("Saint Petersburg", "Nevsky");
        addressField.set(person, newAddr);

    }
}

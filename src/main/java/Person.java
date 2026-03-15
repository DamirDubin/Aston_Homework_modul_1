import java.lang.reflect.Field;

public final class Person {
    private final String name;
    private final Address address;

    public Person(String name, Address address) {
        this.name = name;
        if (address == null) {
            this.address = null;
        } else {
            this.address = new Address(address.getCity(), address.getStreet());
        }
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return new Address(address.getCity(), address.getStreet());
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Address addr = new Address("Moscow", "Arbat");
        Person person = new Person("Ivan", addr);

        //пытаемся изменить адрес
        addr.setCity("Kazan");

        Address retrievedAddr = person.getAddress();
        retrievedAddr.setCity("Penza"); // Это тоже не должно повлиять

        System.out.println(person.getAddress().getCity()); // Должно вывести "Moscow"

        //Меняем private поле name через рефлексию
        Class<? extends Person> aClass = person.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        Field nameField = aClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Vanya");

        //Меняем private поле address через рефлексию
        Field addressField = Person.class.getDeclaredField("address");
        addressField.setAccessible(true); // разрешаем доступ к private полю
        Address newAddr = new Address("Saint Petersburg", "Nevsky");
        addressField.set(person, newAddr);

    }
}

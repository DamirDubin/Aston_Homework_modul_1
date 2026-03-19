import java.util.Optional;

public final class Person {
    private final String name;
    private final Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = Optional.ofNullable(address)
                .map(Address::clone)
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return Optional.ofNullable(address)
                .map(Address::clone)
                .orElse(null);
    }
}

import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public boolean hasAge() {
        return age == 0 ? false : true;
    }

    public boolean hasAddress() {
        return (address == null ? false : true);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (!OptionalInt.of(age).isPresent()) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(age);
    }

    public String getCity() {
        return address;
    }

    public void setAddress(String city) {
        this.address = city;
    }

    public void happyBirthday() {
        age++;
    }


    @Override
    public String toString() {
        return "name " + name + "\n" +
                "surname " + surname + "\n" +
                "age " + (hasAge() ? age : "") + "\n" +
                "address " + (hasAddress() ? address : "") + "\n";
    }

    @Override
    public int hashCode() {
        int result = (name == null && surname == null) ? 0 : name.hashCode() + surname.hashCode();
        result += (address == null ? 0 : address.hashCode());
        result += age;
        return result;
    }

    private Person(ConcretePersonBuilder builder) {
        name = builder.name;
        surname = builder.surname;
        age = builder.age;
        address = builder.address;
    }

    public PersonBuilder newChildBuilder() {
        return new ConcretePersonBuilder()
                .setSurname(this.surname)
                .setAddress(this.address);
    }

    public static class ConcretePersonBuilder implements PersonBuilder {
        private String name;
        private String surname;
        private int age;
        private String address;

        public ConcretePersonBuilder() {
        }

        @Override
        public PersonBuilder setName(String name) {
            if (name == "") {
                throw new IllegalArgumentException("Invalid name");
            }
            if (this.name == null) {
                this.name = name;
            }
            return this;
        }

        @Override
        public PersonBuilder setSurname(String surname) {
            if (surname == "") {
                throw new IllegalArgumentException("Invalid name");
            }
            if (this.surname == null) {
                this.surname = surname;
            }
            return this;
        }

        @Override
        public PersonBuilder setAge(int age) {
            if (age <= 0) {
                throw new IllegalArgumentException("Incorrect age format!");
            } else {
                this.age = age;
            }
            return this;
        }

        @Override
        public PersonBuilder setAddress(String city) {
            this.address = city;
            return this;
        }

        @Override
        public Person build() {
            if (name == null || surname == null) {
                throw new IllegalStateException("Required fields are not filled");
            }
            return new Person(this);
        }
    }

}


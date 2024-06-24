public interface PersonBuilder {
    public PersonBuilder setName(String name);

    public PersonBuilder setSurname(String surname);

    public PersonBuilder setAge(int age);

    public PersonBuilder setAddress(String city);

    public Person build();
}

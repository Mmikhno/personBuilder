public class Main {
    public static void main(String[] args) {
        Person mom = new Person.ConcretePersonBuilder()
                .setName("Anna")
                .setSurname("Volf")
                .setAge(31)
                .setAddress("Some address")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Anton")
                .setAge(2)
                .build();

        try {
            // Не хватает обязательных полей
            new Person.ConcretePersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Возраст недопустимый
            new Person.ConcretePersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}

import javax.annotation.Nullable;

import lombok.Value;

public final class Main {
    public static void main(String[] args) {
        final Person p = new Person("Taro", "Yamada");
        greet(p);
        greet(null);
    }

    private static void greet(Person p) {
        if (p == null) {
            System.out.println("...");
            return;
        }
        System.out.println("Hello " + p.getFirstName());
    }

    @Value
    private static class Person {
        private final String firstName;
        private final String lastName;
    }
}

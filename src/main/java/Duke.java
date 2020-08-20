import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
    }

    private static void greet() {
        System.out.println("Hello! I am Wish\n" + "What can I do for you?");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        ArrayList<String> database = new ArrayList<>(100);
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                list(database);
            } else {
                add(database, s);
                System.out.println("Added item: " + s);
            }
            s = sc.nextLine();
        }

        System.out.println("Goodbye! All the best and see you again soon!");
    }

    private static void add(ArrayList<String> database, String text) {
        database.add(text);
    }

    private static void list(ArrayList<String> database) {
        if (database.size() == 0) {
            System.out.println("There are no items in the database");
        }

        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + ". " + database.get(i));
        }
    }
}

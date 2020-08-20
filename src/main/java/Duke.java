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
        String s = sc.next();

        ArrayList<Task> database = new ArrayList<>(100);
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                list(database);
            } else if (s.equals("done")) {
                if (database.size() != 0) {
                    int index = sc.nextInt();
                    database.get(index - 1).markAsDone();
                    System.out.println("Nice! I have marked this task as done!");
                } else {
                    System.out.println("There are no tasks in the database to mark as done");
                }
            } else {
                add(database, s);
                System.out.println("Added item: " + s);
            }
            s = sc.next();
        }

        System.out.println("Goodbye! All the best and see you again soon!");
    }

    private static void add(ArrayList<Task> database, String text) {
        database.add(new Task(text));
    }

    private static void list(ArrayList<Task> database) {
        if (database.size() == 0) {
            System.out.println("There are no tasks in the database");
        }

        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + ". " + database.get(i));
        }
    }
}

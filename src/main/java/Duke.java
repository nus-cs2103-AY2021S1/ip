import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String separator = "   -------------------------------------------------";

        List<String> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            }
            System.out.println(separator);
            if (next.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(next);
                System.out.println("    added: " + next);
            }
            System.out.println(separator);
            System.out.println();
        }
        sc.close();
        System.out.println(separator);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}

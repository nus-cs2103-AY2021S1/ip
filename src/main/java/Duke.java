import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";

    public static void list(String message) {
        System.out.println(lines + " " + message+ "\n" + lines);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcome = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof*\n";
        String bye = " Bye. Hope to see you again soon! *Woof woof*";

        System.out.println(lines + welcome + lines);

        while (input.hasNext()) {
            String query = input.next();
            if (query.equalsIgnoreCase("bye")) {
                System.out.println(lines + bye + "\n" + lines);
                input.close();
                break;
            } else {
                list(query);
            }
        }

    }
}

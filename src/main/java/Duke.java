import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String separator = "   ----------------------------------------------------------";

        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println(separator);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else {
                Manager.manageInput(next, tasks);
            }
        }
        sc.close();
    }
}

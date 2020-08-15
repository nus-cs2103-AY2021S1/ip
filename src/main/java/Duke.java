import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String message1;

        List<Task> lists = new ArrayList<Task>();

        while (!string1.equals("bye")) {
            if (string1.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < lists.size(); i++) {
                    Task task = lists.get(i);
                    System.out.println((i + 1) + "." + "[" + task.getStatusIcon() + "] "
                            + task.getDescription());
                }
                System.out.println("____________________________________________________________\n");
            } else if (string1.contains("done ")) {
                int order = parseInt(string1.substring(string1.length() - 1));
                Task task = lists.get(order - 1);
                task.markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "  Nice! I've marked this task as done:");
                System.out.println("    [" + task.getStatusIcon() + "] " + task.getDescription());
                System.out.println("____________________________________________________________\n");
            } else {
                lists.add(new Task(string1));
                message1 = "____________________________________________________________\n" +
                        "  added: " + string1 + "\n" +
                        "____________________________________________________________\n";
                System.out.println(message1);
            }
            string1 = scanner.nextLine();
        }

        message1 = "____________________________________________________________\n" +
                "  Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(message1);
    }
}

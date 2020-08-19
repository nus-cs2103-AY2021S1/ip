import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create an empty list of tasks
        ArrayList<Task> tasks = new ArrayList<>();

        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String introduction = "Hello! I'm Duke\nWhat can I do for you?\n";
        String add = "added: ";

        // Print Duke's introduction
        System.out.println(divider + logo + introduction + divider);

        // Read input from input.txt
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.contains("done")) {
                int number = Integer.parseInt(nextLine.substring(5));
                Task task = tasks.get(number - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription() + "\n");
            } else if (nextLine.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    Task task = tasks.get(i);
                    System.out.println(number + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }
                System.out.println();
            } else if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                tasks.add(new Task(nextLine));
                System.out.println(add + nextLine + "\n");
            }
        }
    }
}

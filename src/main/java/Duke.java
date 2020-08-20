import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a personal assistant chatbot to help a person keep track of various things.
 */
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
                System.out.println(task.toString() + "\n");
            } else if (nextLine.equals("list")) {
                System.out.println("Here are the task(s) in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + "." + tasks.get(i).toString());
                }
                System.out.println();
            } else if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                Task task = null;
                if (nextLine.contains("todo")) {
                    String taskString = nextLine.substring(5);
                    task = new Todo(taskString);
                } else if (nextLine.contains("deadline")) {
                    String taskString = nextLine.substring(9);
                    String[] arr = taskString.split(" /by ", 2);
                    task = new Deadline(arr[0], arr[1]);
                } else if (nextLine.contains("event")) {
                    String taskString = nextLine.substring(6);
                    String[] arr = taskString.split(" /at ", 2);
                    task = new Event(arr[0], arr[1]);
                } else {
                    // Do nothing
                }

                if (task != null) {
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    int numOfTasks = tasks.size();
                    if (numOfTasks <= 1) {
                        System.out.println("Now you have " + numOfTasks + " task in the list.\n");
                    } else {
                        System.out.println("Now you have " + numOfTasks + " tasks in the list.\n");
                    }
                }
            }
        }
    }
}

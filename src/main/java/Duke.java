import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                break;
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            }
            else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index));
            }
            else {
                Task newTask = null;
                if (input.startsWith("todo")) {
                    String description = input.substring(5);
                    newTask = new Todo(input);
                }
                else if (input.startsWith("deadline")) {
                    String rest = input.substring(9);
                    int by_position = rest.indexOf("/by ");
                    String description = rest.substring(0, by_position - 1);
                    String by = rest.substring(by_position + 4);
                    newTask = new Deadline(description, by);
                }
                else if (input.startsWith("event")) {
                    String rest = input.substring(6);
                    int at_position = rest.indexOf("/at ");
                    String description = rest.substring(0, at_position - 1);
                    String at = rest.substring(at_position + 4);
                    newTask = new Event(description, at);
                }
                if (newTask != null) {
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.printf("Now you have %d tasks in the list\n", tasks.size());
                }
            }
        }
    }
}

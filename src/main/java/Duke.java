import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String line = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void start() {
         String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
         System.out.println(line);
         System.out.println(logo);
         System.out.println(start);
         System.out.println(line);
    }

    public static void end() {
        String end = "Goodbye! Hope to see you again soon. :)";
        System.out.println(line);
        System.out.println(end);
        System.out.println(line);
    }

    public static void main(String[] args) {
        //start
        start();

        // take in inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            // Show list
            if (input.equals("list")) {
                System.out.println("Here is your to-do list:\n");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }

            // Complete task
            } else if (input.contains("done")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                list.get(taskNumber - 1).completed();
                System.out.println("You've completed this task:\n");
                System.out.println((list.get(taskNumber - 1)).toString());

            // Add task
            } else if (input.contains("todo")) {
                String s = input.substring(5);
                Task task = new Task(s);
                list.add(task);
                System.out.println(line);
                System.out.println("I've added this task:\n");
                System.out.println("[T]" + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in your list.");

            // Add event
            } else if (input.contains("event")) {
                String task = input.substring(6, input.indexOf('/') - 1);
                String date = input.substring(input.lastIndexOf("at"));
                Event event = new Event(task, date);
                list.add(event);
                System.out.println(line);
                System.out.println("I've added this task:\n");
                System.out.println("[E]" + event.toString());
                System.out.println("Now you have " + list.size() + " tasks in your list.");

            // Add deadline
            } else if (input.contains("deadline")) {
                String task = input.substring(9, input.indexOf('/') - 1);
                String date = input.substring(input.lastIndexOf("by"));
                Deadline deadline = new Deadline(task, date);
                list.add(deadline);
                System.out.println(line);
                System.out.println("I've added this task:\n");
                System.out.println("[D]" + deadline.toString());
                System.out.println("Now you have " + list.size() + " tasks in your list.");

            } else {
                // error
            }
            System.out.println(line);
            input = sc.nextLine();
        }

        // end
        end();
    }
}

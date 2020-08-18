import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String str = "";
        System.out.println("    _________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________");

        ArrayList<Task> tasks = new ArrayList<>();

        while (!str.equals("bye")) {
            str = sc.nextLine();
            Task t = new Task(str);

            System.out.println("    _________________________________");


            if (t.getDescription().equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    String item = (i + 1) + ". " + tasks.get(i);
                    System.out.println("    " + item);
                }

            } else if (t.isTodo()) {
                ToDo todo = new ToDo(t.getDescription());
                tasks.add(todo);
                System.out.println("    Got it. I've added this task:");
                System.out.println("    " + todo);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isDeadline()) {
                String date = t.getDate();
                Deadline deadline = new Deadline(t.getDescription(), date);
                tasks.add(deadline);
                System.out.println("    Got it. I've added this task:");
                System.out.println("    " + deadline);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isEvent()) {
                String date = t.getDate();
                Event event = new Event(t.getDescription(), date);
                tasks.add(event);
                System.out.println("    Got it. I've added this task:");
                System.out.println("    " + event);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.getFirstWord().equals("done")) {
                int taskNumber = t.getNumber();
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + tasks.get(taskNumber - 1));

            } else if (!str.equals("bye")) {
                System.out.println("    added: " + t.getDescription());
                tasks.add(t);
            }
            System.out.println("    _________________________________");
        }

        System.out.println("    _________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________");

    }
}

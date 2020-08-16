import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void addedReply(Task task) {
        int num = taskList.size();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.length() >= 4 && input.substring(0,4).equals("done")) {
                    int index = Integer.parseInt(input.substring(5));
                    Task done = taskList.get(index-1);
                    done.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(done);
            } else if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
                String tasking = input.substring(5);
                Task todo = new Todo(tasking);
                taskList.add(todo);
                addedReply(todo);
            } else if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
                String[] split = input.substring(9).split("/by", 2);
                Task deadline = new Deadline(split[0], split[1]);
                taskList.add(deadline);
                addedReply(deadline);
            } else if (input.length() >= 5 && input.substring(0,5).equals("event")) {
                String[] split = input.substring(6).split("/at", 2);
                Task event = new Event(split[0], split[1]);
                taskList.add(event);
                addedReply(event);
            }


            else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else {
                taskList.add(new Task(input));
                System.out.println("Added: " + input);
            }
        }
    }

}

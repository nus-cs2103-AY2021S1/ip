import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);

        greet();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")){
                printList();
            } else if (command.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                done(tasks.get(index - 1));
            } else if (command.split(" ")[0].equals("todo")) {
                String taskcommand = command.replace("todo ", "");
                add(new ToDo(taskcommand));
            } else if (command.split(" ")[0].equals("deadline")) {
                String taskcommand = command.split("/")[0].replace("deadline ", "");
                String time = command.split("/")[1].replace("by ", "");
                add(new Deadline(taskcommand, time));
            } else if (command.split(" ")[0].equals("event")) {
                String taskcommand = command.split("/")[0].replace("event ", "");
                String time = command.split("/")[1].replace("at ", "");
                add(new Event(taskcommand, time));
            } else {
                Task task = new Task(command);
                add(task);
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String command) {
        System.out.println("" + command);
    }

    private static void add(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void done(Task task) {
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }
}

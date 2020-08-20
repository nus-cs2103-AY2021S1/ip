import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList = new ArrayList<>();

    public static String horizontal_line = "____________________________________________________________";

    public static void printWithBorder(String content) {
        System.out.println(horizontal_line);
        System.out.println(content);
        System.out.println(horizontal_line);
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithBorder("Hello from\n" + logo);
    }

    public static void showList() {
        System.out.println(horizontal_line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("%d.%s", i+1, task));
        }
        System.out.println(horizontal_line);
    }

    public static void addTaskMsg(Task task) {
        printWithBorder("Got it. I've added this task: \n "
                + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void response(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        if (words.length == 1) {
            if (command.equals("bye")) {
                printWithBorder("Bye. Hope to see you again soon!");
            }
            if (command.equals("list")) {
                showList();
            }
        }
        if (words.length > 1) {
            String description = input.split(" ", 2)[1];
            if (command.equals("done")) {
                int doneIndex = Integer.parseInt(description);
                Task task = taskList.get(doneIndex - 1);
                task.markAsDone();
                printWithBorder("Nice! I've marked this task as done: \n" + task);
            }
            if (command.equals("todo")) {
                ToDo todo = new ToDo(description);
                taskList.add(todo);
                addTaskMsg(todo);
            }
            if (command.equals("deadline")) {
                String[] contentAndTime = description.split(" /by ");
                Deadline ddl = new Deadline(contentAndTime[0], contentAndTime[1]);
                taskList.add(ddl);
                addTaskMsg(ddl);
            }
            if (command.equals("event")) {
                String[] contentAndTime = description.split(" /at ");
                Event e = new Event(contentAndTime[0], contentAndTime[1]);
                taskList.add(e);
                addTaskMsg(e);
            }
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            response(input);
            input = sc.nextLine();
        }
        response(input);
    }
}

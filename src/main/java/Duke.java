import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "    ____________________________________________________________\n";
    static ArrayList<Task> taskList = new ArrayList<>();

    private static String format(String string) {
        return line + string + "\n" + line;
    }

    private static void list() {
        StringBuilder taskListString = new StringBuilder();
        taskListString.append("     Here are the tasks in your list:\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            taskListString.append("     ").append(i).append(".").append(taskList.get(i - 1)).append("\n");
        }
        System.out.println(format(taskListString.toString()));
    }

    private static void done(String input) {
        int index = Integer.parseInt(input.substring(5));
        if (index <= taskList.size()) {
            Task current = taskList.get(index - 1);
            current.markAsDone();
            System.out.println(format("     Nice! I've marked this task as done:\n       " + current));
        }
    }

    private static void addNewTask(String input) {
        boolean newTaskCreated = false;
        Task newTask = null;
        if (input.startsWith("todo ")) {
            newTask = new Todo(input.substring(5));
            newTaskCreated = true;
        } else if (input.startsWith("deadline ") && input.contains("/")) {
            int index = input.indexOf("/");
            String description = input.substring(9, index);
            String time = input.substring(index + 4);
            newTask = new Deadline(description, time);
            newTaskCreated = true;
        } else if (input.startsWith("event ")  && input.contains("/")) {
            int index = input.indexOf("/");
            String description = input.substring(6, index);
            String time = input.substring(index + 4);
            newTask = new Event(description, time);
            newTaskCreated = true;
        } else {
            System.out.println(format("invalid input"));
        }

        if (newTaskCreated) {
            taskList.add(newTask);
            System.out.println(format("     Got it. I've added this task:\n       " + newTask +
                    "\n     Now you have " + taskList.size() + " tasks in the list."));
        }
    }

    private static void chat() {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            System.out.println(format("     Bye. Hope to see you again soon!"));
        } else {
            if (input.equals("list")) {
                list();
            } else if (input.startsWith("done ")) {
                done(input);
            } else {
                addNewTask(input);
            }
            chat();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format("     Hello! I'm Duke\n" +
                "     What can I do for you?"));
        chat();
    }
}

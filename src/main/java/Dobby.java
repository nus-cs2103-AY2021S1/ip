import java.util.Scanner;
import java.util.ArrayList;

public class Dobby {
    private static final String UNDERSCORE = "____________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        reply("\n    Hello! I'm Dobby\n    How can I help you?\n    ");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            reply(getMessage(text));
            if (text.equals("bye")) {
                System.exit(0);
            }
        }
    }

    private static String getMessage(String text) {
        String message = "";

        if (text.equalsIgnoreCase("bye")) {
            message = "\n    Goodbye. Hope to see you again soon!\n    ";
        } else if (text.equalsIgnoreCase("list")) {
            int i = 0;
            String all_tasks = "\n    ";
            for (Task task : tasks) {
                i++;
                all_tasks = all_tasks + i + ". " + task.getDescription() + "\n    ";
            }
            message = all_tasks;
        } else if (text.startsWith("done")) {
            int index = Integer.parseInt(text.substring(5));
            Task task = tasks.get(index - 1);
            task.setDone();
            message = "\n    Great! I've marked this task as done:\n      " + task.getDescription() + "\n    ";
        } else if (text.startsWith("deadline")) {
            text = text.substring(9);
            String by = text.substring(text.indexOf("/by") + 4);
            text = text.substring(0, text.indexOf("/by") - 1);
            Deadline deadline = new Deadline(text, by);
            tasks.add(deadline);
            message = "\n    Great! I've added the following task:\n      " + deadline.getDescription() +
                    String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
        } else if (text.startsWith("todo")) {
            text = text.substring(5);
            Todo todo = new Todo(text);
            tasks.add(todo);
            message = "\n    Great! I've added the following task:\n      " + todo.getDescription() +
                    String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
        } else if (text.startsWith("event")) {
            text = text.substring(6);
            String at = text.substring(text.indexOf("/at") + 4);
            text = text.substring(0, text.indexOf("/at") - 1);
            Event event = new Event(text, at);
            tasks.add(event);
            message = "\n    Great! I've added the following task:\n      " + event.getDescription() +
                    String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
        } else {
            message =  "\n    Sorry that command is not supported. Please try again.\n   ";
        }

        return message;
    }

    private static void reply(String message) {
        System.out.println("    " + UNDERSCORE + message + UNDERSCORE);
    }
}


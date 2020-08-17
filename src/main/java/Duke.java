import java.util.Scanner;

public class Duke {
    private static final String line = "----------------------";

    // for the list
    private static final Task[] tasks = new Task[100];
    private static int index = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet(logo);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splittedWords = input.split("\\s", 2); // splits string based on whitespace
            String command = splittedWords[0];
            String afterCommand = null;
            if (splittedWords.length > 1) {
                afterCommand = splittedWords[1];
            }

            // handle all different commands
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else if (command.equals("done")){
                int taskNo = Integer.parseInt(afterCommand) - 1;
                tasks[taskNo].markAsCompleted();
                displayCompletedTask(tasks[taskNo]);
            } else if (command.equals("todo")) {
                addOnToList(new ToDo(afterCommand));
            } else if (command.equals("deadline")) {
                // first chunk is the deadline details, second chunk is by when
                String[] splittedDeadline = afterCommand.split("/");
                String details = splittedDeadline[0].trim();
                String by = splittedDeadline[1].split("\\s", 2)[1];
                addOnToList(new Deadline(details, by));
            } else if (command.equals("event")) {
                // first chunk is the event details, second chunk is at where
                String[] splittedEvent = afterCommand.split(("/"));
                String details = splittedEvent[0].trim();
                String at = splittedEvent[1].split("\\s", 2)[1];
                addOnToList(new Event(details, at));
            } else {
                // random adding
                addOnToList(new Task(input));
            }
        }
    }

    private static void greet(String logo) {
       format("Hello! I'm\n" + logo + "\n" +
                "What can I do for you?");
    }

    private static void format(String input) {
        System.out.println(line + "\n" + input + "\n" + line);
    }

    private static void exit() {
        format("Bye. Hope to see you again soon!");
    }

    private static void addOnToList(Task task) {
        tasks[index++] = task;
        format("Got it. I've added this task:\n" + task +  "\n"
            + "Now you have " + index + " tasks in the list.");
    }

    private static void displayList() {
        StringBuilder sb = new StringBuilder();
        if (index == 0) {
            sb.append("No tasks!");
            format(sb.toString());
            return;
        }
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            sb.append(i + 1 + "." + tasks[i] + "\n");
        }
        format(sb.toString());
    }

    private static void displayCompletedTask(Task task) {
        format("Nice! I've marked this task as done:\n" + task);
    }
}

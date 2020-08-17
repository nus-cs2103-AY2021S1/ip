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
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splittedWords = input.split("\\s"); // splits string based on whitespace
            String command = splittedWords[0];

            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else if (command.equals("done")){
                int taskNo = Integer.parseInt(splittedWords[1]);
                tasks[taskNo - 1].markAsCompleted();
                displayCompletedTask(tasks[taskNo - 1]);
            } else {
                addOnToList(new Task(input));
            }
        }
    }

    private static void greet() {
       format("Hello! I'm Duke" + "\n" +
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
        format("added: " + task.details);
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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void printGreeting() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

    private static String createPrompt(String promptText) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(INDENTATION + HORIZONTAL_LINE + '\n');

        for (String line : lines) {
            output.append(INDENTATION).append(line).append('\n');
        }

        output.append(INDENTATION).append(HORIZONTAL_LINE).append('\n');

        return output.toString();
    }

    public static void printPrompt(String promptText) {
        System.out.println(createPrompt(promptText));
    }

    public static void addTask(String description) {
        tasks.add(new Task(description));
        printPrompt("added: " + description);
    }

    public static void doTask(Task task) {
        task.markAsDone();
        printPrompt("Nice! I've marked this task as done:\n  "
                + task);
    }

    public static void listTasks() {
        StringBuilder output = new StringBuilder();

        for (Task task : tasks) {
            output.append(tasks.indexOf(task) + 1).append(".").append(task).append('\n');
        }

        printPrompt(output.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listTasks();
            } else {
                String[] commandList = command.split("\\s+");

                if (commandList[0].equals("done")) {
                    doTask(tasks.get(Integer.parseInt(commandList[1]) - 1));
                } else {
                    addTask(command);
                }
            }

            command = scanner.nextLine();
        }

        printPrompt("Bye. Hope to see you again soon!");
    }
}

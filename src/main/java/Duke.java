import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String GREET_MESSAGE = "Hello! I'm AiBot :)\n\tEnter the command you would like to do\n\tEnter 'bye' to exit";
    private static final String EXIT_MESSAGE = "Bye. See you again soon!";
    private static final String DIVIDER = "\n\t-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";

    private static final List<Task> taskList = new ArrayList<>();

    private static void printResponse(String message) {
        System.out.println(DIVIDER.concat("\t".concat(message.concat("\n"))).concat(DIVIDER));
    }

    private static void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        taskList.add(task);
        printResponse("added: ".concat(taskDescription));
    }

    private static void listTasks() {
        if (taskList.size() == 0) {
            printResponse("No task added yet...");
            return;
        }
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format("%d. %s", (i + 1), taskList.get(i));
            if (i > 0) {
                task = "\n\t".concat(task);
            }
            tasks.append(task);
        }
        printResponse(tasks.toString());
    }

    private static int extractTaskNumber(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        if (words.size() != 2) {
            throw new IllegalArgumentException("Please type \"done [task number]\" to mark task as done");
        } else {
            // convert the task number into int
            int taskNumber = Integer.parseInt(words.get(1));
            return taskNumber;
        }
    }

    private static void doneTask(String command) {
        try {
            int taskNumber = extractTaskNumber(command);
            if (taskNumber > taskList.size()) {
                throw new IllegalArgumentException("The task number is not found");
            }
            taskList.get(taskNumber - 1).markDone();
            String doneMessage = "Nice! I've marked this task as done:";
            doneMessage += String.format("\n\t\t".concat(taskList.get(taskNumber - 1).toString()));
            printResponse(doneMessage);
        } catch (IllegalArgumentException illegalArgumentException) {
            if (illegalArgumentException instanceof NumberFormatException) {
                printResponse("Please type \"done [task number]\" to mark task as done");
            } else {
                printResponse(illegalArgumentException.getLocalizedMessage());
            }
        }
    }

    private static void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                doneTask(command);
            } else {
                addTask(command);
            }
            command = scanner.nextLine();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        printResponse(GREET_MESSAGE);
        listenCommand();
        printResponse(EXIT_MESSAGE);
    }
}

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

    private static String combineWords(List<String> words) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i == 0) {
                message.append(words.get(i));
            } else {
                message.append((" ".concat(words.get(i))));
            }
        }
        return message.toString();
    }

    private static void addTask(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        String taskType = words.get(0);
        Task task;
        if (taskType.equalsIgnoreCase("todo")) {
            if (words.size() <= 1) {
                throw new IllegalArgumentException(":( Oops!!! The description of a Todo task cannot be empty. :-(");
            }
            String taskDescription = combineWords(words.subList(1, words.size()));
            task = new Todo(taskDescription);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            if (words.size() <= 1) {
                throw new IllegalArgumentException(":( Oops!!! The description of a Deadline task cannot be empty. :-(");
            }
            int index = words.indexOf("/by");
            if (index == -1 || words.size() - index <= 1) {
                throw new IllegalArgumentException(":( Oops!!! Please type \"deadline [task description] /by [deadline] \" to add a Deadline task");
            }
            String taskDescription = combineWords(words.subList(1, index));
            String by = combineWords(words.subList(index + 1, words.size()));
            task = new Deadline(taskDescription, by);
        } else if (taskType.equalsIgnoreCase("event")) {
            if (words.size() <= 1) {
                throw new IllegalArgumentException(":( Oops!!! The description of a Event task cannot be empty. :-(");
            }
            int index = words.indexOf("/at");
            if (index == -1 || words.size() - index <= 1) {
                throw new IllegalArgumentException(":( Oops!!! Please type \"event [task description] /at [event time] \" to add a Event task");
            }
            String taskDescription = combineWords(words.subList(1, index));
            String at = combineWords(words.subList(index + 1, words.size()));
            task = new Event(taskDescription, at);
        } else {
            throw new IllegalArgumentException(":( Oops!!! I'm sorry, but I don't know what that means :-(");
        }
        taskList.add(task);
        String message = "Got it. I've added this task:";
        message += "\n\t\t".concat(task.toString());
        message += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        printResponse(message);
    }

    private static void listTasks() {
        if (taskList.size() == 0) {
            printResponse("No task added yet...");
            return;
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format("\n\t%d.%s", (i + 1), taskList.get(i));
            taskMessage.append(task);
        }
        printResponse(taskMessage.toString());
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
                try {
                    addTask(command);
                } catch (IllegalArgumentException illegalArgumentException) {
                    printResponse(illegalArgumentException.getLocalizedMessage());
                }
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

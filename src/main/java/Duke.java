import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "-------------------------------------";

    private static final String HELLO_MESSAGE = "Greetings! I am Duke.\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! See you around!";
    private static final String ERROR_MESSAGE = "Hmm I didn't quite catch that.";

    private static void printWithDivider(String text) {
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";

    private static void processCommand(String input) {
        String[] inputList = input.split(" ");
        if (inputList.length > 0) {
            switch (inputList[0]) {
                case LIST_COMMAND:
                    listTasks();
                    break;
                case DONE_COMMAND:
                    completeTask(inputList);
                    break;
                default:
                    addTask(input);
                    break;
            }
        }
    }

    private static void listTasks() {
        String rv = "You have " + taskList.size() + " items.\n";
        for (int i = 0; i < taskList.size(); i++) {
            rv += "\n" + (i + 1) + ": " + taskList.get(i).toString();
        }
        printWithDivider(rv);
    }

    private static void completeTask(String[] inputList) {
        if (inputList.length > 1) {
            String taskIndex = inputList[1];
            try {
                int index = Integer.parseInt(taskIndex) - 1;
                Task task = taskList.get(index).completeTask();
                printWithDivider("Nice! I've marked this task as done:\n" + task.toString());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                printWithDivider(ERROR_MESSAGE + "\nDid you provide a valid index?");
            }
        } else {
            printWithDivider(ERROR_MESSAGE + "\nWas an index given?");
        }
    }

    private static void addTask(String input) {
        taskList.add(new ToDo(input));
        printWithDivider("Successfully added: " + input);
    }

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printWithDivider(HELLO_MESSAGE);
        while (true) {
            String input = sc.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                printWithDivider(EXIT_MESSAGE);
                break;
            } else {
                processCommand(input);
            }
        }

        sc.close();
    }
}

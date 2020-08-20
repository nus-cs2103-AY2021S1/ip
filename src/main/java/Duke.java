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
    private static final String ADD_TODO = "todo";
    private static final String ADD_EVENT = "event";
    private static final String ADD_DEADLINE = "deadline";
    private static final String DELETE_TASK = "delete";

    private static DukeCommand getDukeCommand(String input) {
        String[] inputList = input.split(" ");
        if (inputList.length > 0) {
            switch (inputList[0]) {
                case LIST_COMMAND:
                    return DukeCommand.LIST;
                case DELETE_TASK:
                    return DukeCommand.DELETE;
                case DONE_COMMAND:
                    return DukeCommand.DONE;
                case ADD_TODO:
                    return DukeCommand.ADD_TODO;
                case ADD_EVENT:
                    return DukeCommand.ADD_EVENT;
                case ADD_DEADLINE:
                    return DukeCommand.ADD_DEADLINE;
                default:
                    return DukeCommand.UNKNOWN;
            }
        } else {
            return DukeCommand.UNKNOWN;
        }
    }

    private static void processInput(String input) {
        StringBuilder commandInput = new StringBuilder();
        StringBuilder argsInput = new StringBuilder();
        boolean commandFound = false;
        for (int i = 0; i < input.length(); i++) {
            if (commandFound) {
                argsInput.append(input.charAt(i));
            } else if (input.charAt(i) == ' ') {
                commandFound = true;
            } else {
                commandInput.append(input.charAt(i));
            }
        }

        String args = argsInput.toString();
        DukeCommand command = getDukeCommand(commandInput.toString());
        try {
            switch (command) {
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    completeTask(args);
                    break;
                case ADD_TODO:
                    addTodo(args);
                    break;
                case ADD_EVENT:
                    addEvent(args);
                    break;
                case ADD_DEADLINE:
                    addDeadline(args);
                    break;
                case DELETE:
                    deleteTask(args);
                    break;
                default:
                    throw new DukeUnknownCommandException(ERROR_MESSAGE + "\nWas the command valid?");
            }
        } catch (DukeUnknownCommandException | DukeIncompleteCommandException | DukeInvalidArgumentException e) {
            printWithDivider(e.getMessage());
        }
    }

    private static void listTasks() {
        String rv = "You have " + taskList.size() + " items.\n";
        for (int i = 0; i < taskList.size(); i++) {
            rv += "\n" + (i + 1) + ": " + taskList.get(i).toString();
        }
        printWithDivider(rv);
    }

    private static void completeTask(String doneIndex)
            throws DukeInvalidArgumentException, DukeIncompleteCommandException {
        try {
            int index = Integer.parseInt(doneIndex) - 1;
            Task task = taskList.get(index).completeTask();
            printWithDivider("Nice! I've marked this task as done:\n" + task.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException(ERROR_MESSAGE + "\nDid you provide a valid index?");
        }
    }

    private static void addTodo(String description) throws DukeIncompleteCommandException {
        if (description.equals("")) {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide any description for this todo task?");
        }
        Todo todo = new Todo(description);
        taskList.add(todo);
        printWithDivider("Successfully added todo:\n" + todo.toString());
    }

    private static void addEvent(String args) throws DukeIncompleteCommandException {
        String[] descWithArgs = args.split(" /at ");
        if (descWithArgs.length == 2) {
            Event event = new Event(descWithArgs[0], descWithArgs[1]);
            taskList.add(event);
            printWithDivider("Successfully added event:\n" + event.toString());
        } else {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide a date and description for this event?");
        }
    }

    private static void addDeadline(String args) throws DukeIncompleteCommandException {
        String[] descWithArgs = args.split(" /by ");
        if (descWithArgs.length == 2) {
            Deadline deadline = new Deadline(descWithArgs[0], descWithArgs[1]);
            taskList.add(deadline);
            printWithDivider("Successfully added deadline:\n" + deadline.toString());
        } else {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide a deadline and description for this deadline?");
        }
    }

    private static void deleteTask(String deleteIndex)
            throws DukeInvalidArgumentException, DukeIncompleteCommandException {
        try {
            int index = Integer.parseInt(deleteIndex) - 1;
            Task task = taskList.remove(index);
            printWithDivider("Noted. I've deleted this task:\n" + task.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException(ERROR_MESSAGE + "\nDid you provide a valid index?");
        }
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
                processInput(input);
            }
        }

        sc.close();
    }
}

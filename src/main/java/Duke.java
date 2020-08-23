import java.time.format.DateTimeParseException;
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
        // convert string command to command action
        switch (input) {
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
        StringBuilder rv = new StringBuilder("You have " + taskList.size() + " items.\n");
        for (int i = 0; i < taskList.size(); i++) {
            rv.append("\n").append(i + 1).append(": ").append(taskList.get(i).toString());
        }
        printWithDivider(rv.toString());
    }

    private static void completeTask(String doneIndex)
            throws DukeInvalidArgumentException {
        try {
            int index = Integer.parseInt(doneIndex) - 1;
            Task task = taskList.get(index).completeTask();
            printWithDivider("Nice! I've marked this task as done:\n" + task.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException(ERROR_MESSAGE + "\nDid you provide a valid index?");
        }
    }

    private static void addTodo(String description) throws DukeIncompleteCommandException {
        try {
            Todo todo = new Todo(description);
            taskList.add(todo);
            printWithDivider("Successfully added todo:\n" + todo.toString());
        } catch (TaskException e) {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide any description for this todo task?");
        }
    }

    private static void addEvent(String args) throws DukeIncompleteCommandException {
        try {
            Event event = Event.create(args);
            taskList.add(event);
            printWithDivider("Successfully added event:\n" + event.toString());
        } catch (TaskException e) {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide a date and description for this event?");
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(ERROR_MESSAGE + "\nPlease provide a valid event date in DD/MM/YYYY HH:mm format.");
        }
    }

    private static void addDeadline(String args) throws DukeIncompleteCommandException {
        try {
            Deadline deadline = Deadline.create(args);
            taskList.add(deadline);
            printWithDivider("Successfully added deadline:\n" + deadline.toString());
        } catch (TaskException e) {
            throw new DukeIncompleteCommandException(ERROR_MESSAGE
                    + "\nDid you provide a deadline and description for this deadline?");
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(ERROR_MESSAGE + "\nPlease provide a valid deadline in DD/MM/YYYY HH:mm format.\"");
        }
    }

    private static void deleteTask(String deleteIndex)
            throws DukeInvalidArgumentException {
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

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // Constants related to display (including messages)
    private static final String LINE_BREAK = "\t____________________"
            + "________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Tusk\n"
            + "\tWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. "
            + "Hope to see you again soon!";
    private static final String TOGGLE_MESSAGE = "Nice! "
            + "I've marked this task as done:\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String DELETED_MESSAGE = "Noted. I've removed this task:\n";
    private static final String TASK_COUNT_FRONT = "Now you have ";
    private static final String TASK_COUNT_END = " task(s) in the list.";

    // Command constants for the bot
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    // static fields for the bot
    private static ArrayList<Task> store = new ArrayList<>(); // add input items here
    private static boolean isFirstRun = true; // is the bot run for the first time?

    // helper methods related to displaying
    private static void displayToScreen(String str) {
        System.out.println(LINE_BREAK);
        System.out.println("\t" + str);
        System.out.println(LINE_BREAK + "\n");
    }
    private static void displayStoreItems() {
        System.out.println(LINE_BREAK);
        System.out.println("\t" + LIST_MESSAGE);
        for (int ctr = 1; ctr <= store.size(); ctr++) {
            System.out.println("\t" + ctr + ". " + store.get(ctr - 1));
        }
        System.out.println(LINE_BREAK + "\n");
    }
    private static void displayModifiedTasks(Task t, boolean isAdding) {
        String message = isAdding ? ADDED_MESSAGE : DELETED_MESSAGE;
        System.out.println(LINE_BREAK);
        System.out.println("\t" + message);
        System.out.println("\t  " + t);
        System.out.println("\t" + TASK_COUNT_FRONT + store.size() + TASK_COUNT_END);
        System.out.println(LINE_BREAK + "\n");
    }

    // helper methods related to commands
    // can consider using Enum for taskType
    private static void taskHandler(String input, String taskType) throws DukeException {
        switch (taskType) {
        case TODO_COMMAND:
            try {
                String todoName = input.substring(input.indexOf("todo") + 5);

                // case: check for "whitespace description"
                if (todoName.matches("\\s+")) {
                    throw DukeException.emptyDescription("todo");
                }

                // remove leading and trailing whitespace
                Task newTodo = new ToDo(false, todoName.trim());
                store.add(newTodo);
                displayModifiedTasks(newTodo, true);
            } catch (StringIndexOutOfBoundsException noDesc) {
                // case: No description
                throw DukeException.emptyDescription("todo");
            }
            break;
        case EVENT_COMMAND:
            try {
                int eventMarker = input.indexOf("/at");
                // case: no tag
                if (eventMarker == -1) {
                    throw new DukeException("The '/at' tag is missing");
                }

                String eventTime = input.substring(eventMarker + 4);
                String eventName = input.substring(6, eventMarker - 1);

                // case: check for "whitespace description or timeline"
                if (eventTime.matches("\\s+") || eventName.matches("\\s+")) {
                    throw DukeException.emptyDescription("event");
                }

                // remove leading and trailing whitespace
                Task newEvent = new Event(false, eventName.trim(), eventTime.trim());
                store.add(newEvent);
                displayModifiedTasks(newEvent, true);
            } catch (StringIndexOutOfBoundsException noDescOrTimeEvent) {
                // case: Either no description or no timeline
                throw DukeException.emptyDescription("event");
            }
            break;
        case DEADLINE_COMMAND:
            try {
                int deadlineMarker = input.indexOf("/by");
                // case: no tag
                if (deadlineMarker == -1) {
                    throw new DukeException("The '/by' tag is missing");
                }

                String deadlineTime = input.substring(deadlineMarker + 4);
                String deadlineName = input.substring(9, deadlineMarker - 1);

                // case: check for "whitespace description or timeline"
                if (deadlineTime.matches("\\s+") || deadlineName.matches("\\s+")) {
                    throw DukeException.emptyDescription("deadline");
                }

                // remove leading and trailing whitespace
                Task newDeadline = new Deadline(false, deadlineName.trim(), deadlineTime.trim());
                store.add(newDeadline);
                displayModifiedTasks(newDeadline, true);
            } catch (StringIndexOutOfBoundsException noDescOrTimeDeadline) {
                // case: Either no description or no timeline
                throw DukeException.emptyDescription("deadline");
            }
            break;
        default:
            break;
        }
    }

    private static void doneHandler(String number) throws DukeException {
        // check for invalid input
        try {
            int index = Integer.parseInt(number);
            // outside of valid range
            if (index <= 0 || index > store.size()) {
                throw DukeException.invalidNumberInput();
            }

            Task toSet = store.get(index - 1);
            toSet.setTaskAsDone();
            displayToScreen(TOGGLE_MESSAGE + "\t " + toSet);
        } catch (NumberFormatException e) {
            throw DukeException.invalidNumberInput();
        }
    }

    private static void deleteHandler(String number) throws DukeException {
        // check for invalid input
        try {
            int index = Integer.parseInt(number);
            // outside of valid range
            if (index <= 0 || index > store.size()) {
                throw DukeException.invalidNumberInput();
            }

            Task toDelete = store.remove(index - 1);
            ArrayList<Task> newList = new ArrayList<>();
            newList.addAll(store);
            store = newList;
            displayToScreen(DELETED_MESSAGE + "\t  " + toDelete
                    + "\n\t" + TASK_COUNT_FRONT + store.size() + TASK_COUNT_END);
        } catch (NumberFormatException e) {
            throw DukeException.invalidNumberInput();
        }
    }

    public static void main(String[] args) {
        // initialize scanner and add commands to set
        Scanner sc = new Scanner(System.in);

        // display welcome message
        if (isFirstRun) {
            isFirstRun = false; // it's not first run anymore
            displayToScreen(WELCOME_MESSAGE);
        }

        // read input
        String input = sc.nextLine();

        // add to store if the command is not "list" or "bye"
        // if comment is "list", display added items using displayStoreItems
        while (!input.equals(EXIT_COMMAND)) {
            try {
                // split input into individual words and get command
                String[] words = input.split("\\s+");
                String command = words[0];

                // handle for different commands
                switch (command) {
                    case LIST_COMMAND:
                        displayStoreItems();
                        break;
                    case TODO_COMMAND:
                        taskHandler(input, "todo");
                        break;
                    case EVENT_COMMAND:
                        taskHandler(input, "event");
                        break;
                    case DEADLINE_COMMAND:
                        taskHandler(input, "deadline");
                        break;
                    case DONE_COMMAND:
                        doneHandler(words[1]);
                        break;
                    case DELETE_COMMAND:
                        deleteHandler(words[1]);
                        break;
                    default:
                        throw DukeException.unknownOperation();
                }
                input = sc.nextLine(); // continue reading input
            } catch (DukeException e) {
                displayToScreen(e.getMessage());
                input = sc.nextLine(); // continue reading input
            }
        }

        // line reached upon command "bye", at which point quit and echo exit message
        displayToScreen(EXIT_MESSAGE);
    }
}

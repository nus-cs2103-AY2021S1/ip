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
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String TOGGLE_MESSAGE = "Nice! "
            + "I've marked this task as done:\n";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_FRONT = "Now you have ";
    private static final String TASK_COUNT_END = " task(s) in the list.";


    // Command constants for the bot
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

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
    private static void displayTasks(Task t) {
        System.out.println(LINE_BREAK);
        System.out.println("\t" + ADDED_MESSAGE);
        System.out.println("\t  " + t);
        System.out.println("\t" + TASK_COUNT_FRONT + store.size() + TASK_COUNT_END);
        System.out.println(LINE_BREAK + "\n");
    }

    // helper methods related to commands
    // can consider using Enum for taskType
    private static void taskHandler(String input, String taskType) {
        switch (taskType) {
        case TODO_COMMAND:
            String todoName = input.substring(input.indexOf("todo") + 5);

            // remove leading and trailing whitespace
            Task newTodo = new ToDo(false, todoName.strip());
            store.add(newTodo);
            displayTasks(newTodo);
            break;
        case EVENT_COMMAND:
            int eventMarker = input.indexOf("/at");

            String eventTime = input.substring(eventMarker + 4);
            String eventName = input.substring(6, eventMarker - 1);

            // remove leading and trailing whitespace
            Task newEvent = new Event(false, eventName.strip(), eventTime.strip());
            store.add(newEvent);
            displayTasks(newEvent);
            break;
        case DEADLINE_COMMAND:
            int deadlineMarker = input.indexOf("/by");

            String deadlineTime = input.substring(deadlineMarker + 4);
            String deadlineName = input.substring(9, deadlineMarker - 1);

            // remove leading and trailing whitespace
            Task newDeadline = new Deadline(false, deadlineName.strip(), deadlineTime.strip());
            store.add(newDeadline);
            displayTasks(newDeadline);
            break;
        default:
            break;
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

        while (!input.equals(EXIT_COMMAND)) {
            String[] words = input.split("\\s+");
            String command = words[0];

            switch (command) {
            case TODO_COMMAND:
                taskHandler(input, "todo");
                break;
            case EVENT_COMMAND:
                taskHandler(input, "event");
                break;
            case DEADLINE_COMMAND:
                taskHandler(input, "deadline");
                break;
            case LIST_COMMAND:
                displayStoreItems();
                break;
            case DONE_COMMAND:
                Task toSet = store.get(Integer.parseInt(words[1]) - 1);
                toSet.setTaskAsDone();
                displayToScreen(TOGGLE_MESSAGE + "\t " + toSet);
                break;
            default:
                store.add(new Task(false, input));
                displayToScreen("added: " + input);
                break;
            }
            input = sc.nextLine();
        }

        // line reached upon command "bye", at which point quit and echo exit message
        displayToScreen(EXIT_MESSAGE);
    }
}

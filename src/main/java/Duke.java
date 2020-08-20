import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static boolean isEmptyInput(String input) {
        return input.isEmpty();
    }

    public static boolean isValidCommand(String input) {
        return input.toLowerCase().startsWith("todo")
                || input.toLowerCase().startsWith("deadline")
                || input.toLowerCase().startsWith("event");
    }

    public static boolean isEmptyDescription(String input) {
        return input.split(" ").length == 1;
    }

    public static boolean hasDeadlineBy(String input) {
        return input.contains("/by")
                && input.split(" /by ").length == 2;
    }

    public static boolean hasEventStartEndTime(String input) {
        return input.contains("/at")
                && input.split(" /at ").length == 2
                && input.split(" /at ")[1].contains("-")
                && input.split(" /at ")[1].split("-").length == 2;
    }

    public static void main(String[] args) {

        // Initialise strings to separate messages from Duke
        // and commands from CLI
        String servantSpeak = "Duke:\n";
        String masterSpeak = "Your Command Sire:";

        // Introduction at the beginning of the chat
        System.out.println(servantSpeak
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");

        // Initialise the Scanner object to get input from user
        Scanner myObj = new Scanner(System.in);
        String input;

        // Initialise DukeException object
        DukeException dE = new DukeException();

        // Initialise ArrayList to store tasks from user
        ArrayList<Task> userTasks = new ArrayList<Task>();

        // Start chat
        while (true) {
            // Get input from user
            System.out.println(masterSpeak);
            input = myObj.nextLine().trim();
            System.out.println();

            // If user inputs "bye" in any case, end the chat
            if (input.equals("bye")) {
                System.out.println(servantSpeak
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }

            // If user marks "done" mark task as isDone = true
            if (input.contains("done ")) {
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Check if index is out of bounds
                if (index >= userTasks.size()) {
                    System.out.println(servantSpeak
                            + dE.MESSAGE_INDEX_OUT_OF_BOUNDS);
                } else {
                    userTasks.get(index).setDone();
                    System.out.println(servantSpeak
                            + "    As you wish Sire. I have marked this task as done:\n"
                            + "       " + userTasks.get(index).toString());
                }
                System.out.println();
                continue;
            }

            // If user requests delete, delete selected task
            if (input.contains("delete ")) {
                // Get the index stated after "delete" by parsing the string
                int index = Integer.parseInt(input.substring(7)) - 1;

                // Check if index is out of bounds
                if (index >= userTasks.size()) {
                    System.out.println(servantSpeak
                            + dE.MESSAGE_INDEX_OUT_OF_BOUNDS);
                } else {
                    System.out.println(servantSpeak
                            + "    As you wish Sire. I removed this task:\n"
                            + "       " + userTasks.get(index).toString());
                    userTasks.remove(index);
                }
                System.out.println();
                continue;
            }

            // If user requests for list, display list of tasks
            if (input.equals("list")) {
                int count = 1;
                System.out.println(servantSpeak
                        + "    Here are your tasks your Majesty:");
                for (Task i : userTasks) {
                    System.out.println("    "
                            + count + ". "
                            + i.toString());
                    count++;
                }
                System.out.println();
                continue;
            }

            // Check validity of input command
            if (isEmptyInput(input)) {
                System.out.println(servantSpeak
                        + dE.MESSAGE_ALL_TASKS_EMPTY_INPUT);
                continue;
            }
            if (!isValidCommand(input)) {
                System.out.println(servantSpeak
                        + dE.MESSAGE_ALL_TASKS_INVALID_COMMAND);
                continue;
            }
            if (isEmptyDescription(input)) {
                System.out.println(servantSpeak
                        + dE.MESSAGE_ALL_TASKS_EMPTY_DESCRIPTION);
                continue;
            }

            // Determine what kind of task it is
            Task t;
            String[] inputSplit;
            String description;
            switch (input.toLowerCase().split(" ")[0]) {
                case "todo":
                    description = input.substring(4);
                    t = new ToDo(description);
                    userTasks.add(t);
                    break;
                case "deadline":
                    if (!hasDeadlineBy(input)) {
                        System.out.println(servantSpeak
                                + dE.MESSAGE_DEADLINE_NO_BY);
                        continue;
                    }
                    inputSplit = input.split(" /by ");
                    String by = inputSplit[1];
                    description = inputSplit[0].substring(8);
                    t = new Deadline(description, by);
                    userTasks.add(t);
                    break;
                case "event":
                    if (!hasEventStartEndTime(input)) {
                        System.out.println(servantSpeak
                                + dE.MESSAGE_EVENT_NO_START_END);
                        continue;
                    }
                    inputSplit = input.split(" /at ");
                    String start = inputSplit[1].split("-")[0];
                    String end = inputSplit[1].split("-")[1];
                    description = inputSplit[0].substring(5);
                    t = new Event(description, start, end);
                    userTasks.add(t);
                    break;
            }

            // Standard reply from Duke for adding a task
            System.out.println(servantSpeak
                    + "    As you wish Sire. I have added the task:\n       "
                    + userTasks.get(userTasks.size() - 1).toString() + "\n"
                    + "    Now you have " + userTasks.size()
                    + " tasks in the list.\n");

        }

    }
}
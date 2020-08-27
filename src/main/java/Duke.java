import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
    }

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
                && input.split(" /at ")[1].split(" ").length == 2
                && input.split(" /at ")[1].split(" ")[1]
                .split("-").length == 2;
    }

    public void run() {
        // Print Duke welcome message
        ui.welcomeMessage();

        // Initialise the Scanner object to get input from user
        Scanner myObj = new Scanner(System.in);
        String input;

        // Initialise ArrayList to store tasks from user
        ArrayList<Task> userTasks;

        // Read tasks stored in hard drive if any
        userTasks = storage.readFromFile();

        // Start chat
        while (true) {
            // Get input from user
            System.out.println(ui.getUserPrompt());
            input = myObj.nextLine().trim();
            System.out.println();

            // If user inputs "help", print list of available commands
            if (input.equals("help")) {
                ui.availableCommands();
                continue;
            }

            // If user inputs "bye" in any case, end the chat
            if (input.equals("bye")) {
                System.out.println(ui.getServantSpeak()
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }

            // If user marks "done" mark task as isDone = true
            if (input.contains("done ")) {
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Mark item as done
                try {
                    if (index >= userTasks.size()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        userTasks.get(index).setDone();
                        ui.printMarkAsDoneMessage(userTasks.get(index).toString());
                    }

                    // Update Tasklist.txt after marking task as done
                    storage.saveToFile(userTasks);

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            }

            // If user requests delete, delete selected task
            if (input.contains("delete ")) {
                // Get the index stated after "delete" by parsing the string
                int index = Integer.parseInt(input.substring(7)) - 1;

                // Delete item
                try {
                    if (index >= userTasks.size()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        ui.printTaskDeletedMessage(userTasks.get(index).toString());
                        userTasks.remove(index);
                    }

                    // Update Tasklist.txt after removing task
                    storage.saveToFile(userTasks);

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            }

            // If user requests for list, display list of tasks
            if (input.equals("list")) {
                ui.printListOfTasks(userTasks);
                continue;
            }

            // Check validity of input command
            try {
                if (isEmptyInput(input)) {
                    throw new DukeException("", ExceptionType.EMPTY_INPUT);
                }
                if (!isValidCommand(input)) {
                    throw new DukeException("", ExceptionType.INVALID_COMMAND);
                }
                if (isEmptyDescription(input)) {
                    throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
                }
            } catch (DukeException ex) {
                System.out.print(ui.getServantSpeak());
                ui.printError(ex);
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
                ui.printTaskAddedMessage(t.toString(), userTasks.size());
                break;
            case "deadline":
                try {
                    if (!hasDeadlineBy(input)) {
                        throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
                    }
                    inputSplit = input.split(" /by ");
                    String by = inputSplit[1];
                    description = inputSplit[0].substring(8);
                    t = new Deadline(description, by);
                    userTasks.add(t);
                    ui.printTaskAddedMessage(t.toString(), userTasks.size());
                    break;
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case "event":
                try {
                    if (!hasEventStartEndTime(input)) {
                        throw new DukeException("", ExceptionType.EVENT_NO_START_END);
                    }
                    inputSplit = input.split(" /at ");
                    String at = inputSplit[1].split(" ")[0];
                    String timeRange = inputSplit[1].split(" ")[1];
                    description = inputSplit[0].substring(5);
                    t = new Event(description, at, timeRange);
                    userTasks.add(t);
                    ui.printTaskAddedMessage(t.toString(), userTasks.size());
                    break;
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            }

            // Update Tasklist.txt after adding task
            storage.saveToFile(userTasks);
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
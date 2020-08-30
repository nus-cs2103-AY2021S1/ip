package duke;

public class Duke {

    private Ui ui;
    private Storage storage;
    private InputParser inputParser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.inputParser = new InputParser();
    }

    public void run() throws DukeException {
        // Print Duke welcome message
        ui.welcomeMessage();

        String input;

        // Initialise TaskList object to store tasks from user
        TaskList userTasks = new TaskList(storage.readFromFile());
        CommandType command = CommandType.HELP;
        // Start chat
        while (command != CommandType.BYE) {
            Task t;
            String[] inputSplit;
            String description;

            // Get input from user
            System.out.println(ui.getUserPrompt());
            input = inputParser.getInput();
            System.out.println();

            command = inputParser.parseInput(input);

            switch (command) {
            case HELP:
                ui.printAvailableCommands();
                continue;
            case TODO:
                description = input.substring(4);
                t = new ToDo(description);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case DEADLINE:
                inputSplit = input.split(" /by ");
                String by = inputSplit[1];
                description = inputSplit[0].substring(8);
                t = new Deadline(description, by);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case EVENT:
                inputSplit = input.split(" /at ");
                String at = inputSplit[1].split(" ")[0];
                String timeRange = inputSplit[1].split(" ")[1];
                description = inputSplit[0].substring(5);
                t = new Event(description, at, timeRange);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case LIST:
                ui.printListOfTasks(userTasks.getTaskList());
                continue;
            case DONE:
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Mark item as done
                try {
                    if (index >= userTasks.getTaskListSize()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        userTasks.markTaskAsDone(index);
                        ui.printMarkAsDoneMessage(userTasks.getTask(index));
                    }

                    // Update Tasklist.txt after marking task as done
                    storage.saveToFile(userTasks.getTaskList());

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            case DELETE:
                // Get the index stated after "delete" by parsing the string
                index = Integer.parseInt(input.substring(7)) - 1;

                // Delete item
                try {
                    if (index >= userTasks.getTaskListSize()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        ui.printTaskDeletedMessage(userTasks.getTask(index));
                        userTasks.deleteTask(index);
                    }

                    // Update Tasklist.txt after removing task
                    storage.saveToFile(userTasks.getTaskList());

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            case BYE:
                ui.printByeMessage();
                continue;
            case INVALID_IS_EMPTY:
                try {
                    throw new DukeException("", ExceptionType.EMPTY_INPUT);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_COMMAND:
                try {
                    throw new DukeException("", ExceptionType.INVALID_COMMAND);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_EMPTY_DESCRIPTION:
                try {
                    throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_DEADLINE_NO_BY:
                try {
                    throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_EVENT_NO_START_END:
                try {
                    throw new DukeException("", ExceptionType.EVENT_NO_START_END);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            }

        }
    }
}
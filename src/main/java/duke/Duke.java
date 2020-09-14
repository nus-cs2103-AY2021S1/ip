package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.ui.TextUi;

/**
 * Duke is the main program where most of the logic is
 * processed. It is a task manager that records tasks
 * based on user input, with the option to list all
 * task, find a particular one, delete tasks, etc.
 */
public class Duke {

    private static final String GREETING =
            "Hi, I'm your Professor, Elon. I keep track of\n"
                    + "your tasks and load them if you have any saved.\n";
    private static final String HELP_MESSAGE =
            "You can ask me to do these:\n"
                    + "list: List the current tasks in your list.\n"
                    + "bye: Saves any tasks in the list and quits the program.\n"
                    + "todo: Add a To-Do task.\n"
                    + "event: Add an event task.\n"
                    + "deadline: Add a deadline task.\n"
                    + "done: Mark task as done based on the task's number.\n"
                    + "delete: Deletes task based on the task's number.\n"
                    + "find: Find a task which matches your description."
                    + "help: Repeat this list of possible commands.";
    private final Storage storage;
    private TaskList taskList;
    private final TextUi textUi;
    private boolean isRunning;

    /**
     * Initialise Duke with filePath of the saved list of tasks.
     */
    public Duke() {
        this.textUi = new TextUi();
        this.storage = new Storage();
        this.isRunning = true;
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            textUi.showException(e);
            taskList = new TaskList();
        }
    }

    /**
     * Main control system which runs Duke in the Command line.
     */
    private void runInConsole() {
        textUi.showMessage(GREETING);
        textUi.showMessage(HELP_MESSAGE);
        textUi.showLine();
        // Loop program until command 'bye' is entered as input.
        while (isRunning) {
            try {
                String input = textUi.readCommand();
                String response = getResponse(input);
                textUi.showMessage(response);
            } catch (DukeException e) {
                textUi.showException(e);
            } finally {
                textUi.showLine();
            }
        }
        textUi.closeUi();
    }

    public String getResponse(String input) throws DukeException {
        Command command = Parser.getCommand(input, taskList, storage);
        String response = command.executeWithResponse();
        isRunning = command.continueDuke();
        return response;
    }

    public boolean checkIsRunning() {
        return isRunning;
    }

    public String getGreeting() {
        return GREETING;
    }

    public String getHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Initialise program with a new instance of Duke.
     *
     * @param args String array passed into main.
     */
    public static void main(String[] args) {
        Duke dukeProgram = new Duke();
        dukeProgram.runInConsole();
    }
}

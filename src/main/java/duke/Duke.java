package duke;

import duke.command.Command;
import duke.task.TaskList;

/** Driver class for duke.Duke chat bot called "Jarvis" */
public class Duke {
    private static final String STORAGE_PATH = "data/duke.txt";
    private final Storage storage;
    private final CommandAgent agent;
    private final Ui ui;

    /**
     * Constructs an instance for Duke class.
     * Takes in a file path to initialize storage.
     * Initializes a command agent and a user interface.
     *
     * @param filePath The path of task list data stored in the hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
        }

        agent = new CommandAgent(tasks);
    }

    /**
     * Runs the program by taking in the command, handling them, storing data to
     * hard disk, and returning to users appropriate response.
     */
    public void run() {
        ui.showWelcomeMessage();
        runUntilExit();
    }

    /**
     * Runs the program until an exit command is called by the user.
     */
    public void runUntilExit() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                agent.handleCommand(c, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Executes the "Jarvis" bot to run by loading the task list stored in <kbd>data/duke.txt</kbd>.
     *
     * @param args The main() function arguments.
     */
    public static void main(String[] args) {
        assert STORAGE_PATH.contains(".txt") : "The storage path must contains a valid .txt file";

        new Duke(STORAGE_PATH).run();
    }

    /**
     * Get the response from the chat bot given the user input as a command.
     *
     * @param input The command input user types in.
     * @return The response from duke command agent.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return agent.handleCommand(c, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

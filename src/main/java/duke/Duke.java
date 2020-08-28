package duke;

import duke.command.Command;
import duke.task.TaskList;

/** Driver class for duke.Duke chat bot called "Jarvis" */
public class Duke {
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
     * The running will terminate when an ExitCommand is called.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
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
        new Duke("data/duke.txt").run();
    }

    /**
     * Get the response from the chat bot given the user input as a command.
     *
     * @param input The command input user types in.
     * @return The response from duke command agent.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return agent.handleCommand(c, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

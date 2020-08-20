package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Driver class for duke.Duke chat bot called "Jarvis"
 */
public class Duke {

    private final Storage storage;
    private final CommandAgent agent;
    private final Ui ui;

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
     * Run the program by taking in the command, handling them, storing data to
     * hard disk, and return to users appropriate feedbacks.
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
     * Executes the "Jarvis" bot to run.
     * By loading the task list stored in <kbd>data/duke.txt</kbd>.
     *
     * @param args main() function arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

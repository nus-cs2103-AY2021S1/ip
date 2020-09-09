package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.utils.Parser;
import duke.utils.Ui;

/**
 * Represents a chat bot call duke.Duke.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke(String path, String fileName) {
        ui = new Ui();
        tasks = new TaskList(path, fileName);
    }

    /**
     * The chat bot starts running and interact with user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.sayGoodBye();
    }

    public String getResponse(String command) throws DukeException {
        Command c = Parser.parse(command);
        return c.execute(tasks, ui);
    }

    public static void main(String[] args) {
        new Duke("data/", "duke.txt").run();
    }

}

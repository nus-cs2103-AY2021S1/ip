package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Duke chat-bot, called Nite.
 * Nite is an interactive app which helps to keep track of tasks.
 * @author Chia Wen Ling
 * @version v0.1
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke Chat-bot.
     *
     * @param filePath Directory where duke.Duke text file is saved.
     * @param folderPath Path name of duke.Duke text file to be saved.
     */
    public Duke(String filePath, String folderPath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, folderPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public Duke() {
    }

    /**
     * Initialises the Ui, Storage and TaskList upon starting.
     */
    public void initialise() {
        ui = new Ui();
        storage = new Storage("/data/duke.txt", "/data");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response for the input command.
     *
     * @param input User input.
     * @return Response to user's input.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            if (isExit) {
                //                System.exit(0);
                return ui.showFarewell();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Main method for starting the chat-bot.
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        new Duke("/data/duke.txt", "/data").run();
    }
}

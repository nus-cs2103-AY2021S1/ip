package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the chat bot Duke.
 * @version 1.0
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke chat bot with the specified file path for storage.
     *
     * @param filePath A String representation of the target file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        assert filePath != "" : "Empty file path.";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chat bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.translate(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream dukeDialog = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(dukeDialog);
        String fullCommand = input;
        Command c = Parser.translate(fullCommand);
        c.execute(tasks, ui, storage);
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}

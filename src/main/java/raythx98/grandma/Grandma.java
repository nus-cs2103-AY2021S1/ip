package raythx98.grandma;

import raythx98.grandma.command.Command;
import raythx98.grandma.exception.DukeException;
import raythx98.grandma.parser.Parser;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Something.
 */
public class Grandma {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Something.
     * @param filePath
     */
    public Grandma(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            ui.resetString();
            Command command = parser.parse(tasks, input);
            command.execute(tasks, ui, storage);
            return ui.finalShowOnScreen();
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (Exception e) {
            return ui.showUncheckedException();
        }
    }

    /**
     * Something.
     *
     * @return
     */
    public String getGreeting() {
        return ui.greet();
    }
}

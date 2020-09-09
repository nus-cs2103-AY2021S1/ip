package raythx98.grandma;

import java.io.FileNotFoundException;

import raythx98.grandma.command.Command;
import raythx98.grandma.exception.DukeException;
import raythx98.grandma.parser.Parser;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Represents the grandma bot.
 */
public class Grandma {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * initialises Grandma and other class variables.
     *
     * @param filePath specified filepath to store the task information.
     */
    public Grandma(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.getError(e);
        } catch (FileNotFoundException e) {
            tasks = storage.newTasks();
        }
    }

    /**
     * Takes a string as an input from user and returns string to output in Grandma.
     *
     * @param input string input from user.
     * @return string to be shown on the screen.
     */
    public String getResponse(String input) {
        try {
            ui.resetTextOnScreen();
            Command command = parser.parse(tasks, input);
            command.execute(tasks, ui, storage);
            return ui.getTextOnScreen();
        } catch (DukeException e) {
            return ui.getError(e);
        }
//        catch (Exception e) {
//            return ui.getUncheckedException();
//        }
    }

    /**
     * Get greeting message from ui.
     *
     * @return a greeting message String.
     */
    public String getGreeting() {
        return ui.greetingMessage();
    }
}

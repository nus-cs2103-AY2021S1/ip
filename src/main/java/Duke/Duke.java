package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.logic.CommandParser;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a todo manager bot.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final CommandParser parser;
    private TaskList tasks;

    /**
     * Constructs a Duke bot.
     * @param filePath the path of the file which store the previous data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new CommandParser(ui, storage, tasks);
    }

    /**
     * Displays response in GUI.
     * @param input is the command input.
     * @return the response for the command.
     */
    public String getResponse(String input) {
        String res = "";
        try {
            res = parser.parse(input);
        } catch (NumberFormatException e) {
            res = ui.showNumberFormatError();
        } catch (DukeException e) {
            res = ui.showDukeError(e);
        } catch (FileNotFoundException e) {
            res = ui.showFileNotFoundError();
        } catch (DateTimeException e) {
            res = ui.showDateTimeError();
        }
        return res;
    }
}

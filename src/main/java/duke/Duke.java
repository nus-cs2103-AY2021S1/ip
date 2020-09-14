package duke;

import java.io.File;

import duke.backend.Storage;
import duke.command.Command;
import duke.exception.DukeEmptyAtException;
import duke.exception.DukeEmptyByException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeEmptyIndexException;
import duke.exception.DukeEmptyKeywordException;
import duke.exception.DukeInvalidDataException;
import duke.exception.DukeInvalidDateTimeInputException;
import duke.response.Parser;
import duke.response.Response;
import duke.task.TaskList;

/**
 * Represents the chat bot.
 * It is the Main class.
 */
public class Duke {
    private TaskList list;
    private final Storage storage;
    private final Response ui;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new Response();
        String filePath = System.getProperty("user.home") + "/data";
        checkAndMakeDir(filePath);
        filePath += "/duke.txt";
        storage = new Storage(filePath);

        try {
            list = new TaskList(storage.load());
        } catch (DukeInvalidDataException e) {
            ui.showLoadingError();
            list = new TaskList();
        } catch (DukeInvalidDateTimeInputException e) {
            ui.showError(e);
            list = new TaskList();
        }
    }

    /**
     * Checks to see if directory is found.
     * Creates the directory if directory is not found.
     *
     * @param filePath The file path of the directory.
     */
    public static void checkAndMakeDir(String filePath) {
        File f = new File(filePath);
        f.mkdir();
    }

    /**
     * Returns the response message to be shown on UI.
     *
     * @param input The user input.
     * @return Response message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, storage);
        } catch (DukeEmptyIndexException e) {
            return e.getMessage();
        } catch (DukeEmptyDescriptionException e) {
            return e.getMessage();
        } catch (DukeEmptyAtException e) {
            return e.getMessage();
        } catch (DukeEmptyByException e) {
            return e.getMessage();
        } catch (DukeInvalidDateTimeInputException e) {
            return e.getMessage();
        } catch (DukeEmptyKeywordException e) {
            return e.getMessage();
        }
    }
}

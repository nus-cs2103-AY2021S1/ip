package duke;

import duke.command.Command;
import duke.command.Response;
import duke.exceptions.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The {@code Duke} class controls the logic of the chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of the chat bot logic.
     * Uses the default save location.
     *
     * @param useHistory true if the local save file at the default save location should be loaded.
     */
    public Duke(boolean useHistory) {
        this(Storage.FILE_PATH, useHistory);
    }

    /**
     * Constructs an instance of the chat bot logic.
     *
     * @param filePath the file path to store the local save file.
     * @param useHistory true if the local save file should be loaded.
     * @throws DukeStorageException if the file at the specified {@code filePath} could not be loaded.
     */
    public Duke(String filePath, boolean useHistory) throws DukeStorageException {
        if (useHistory) {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } else {
            storage = new Storage(filePath);
            taskList = new TaskList();
        }
    }

    /**
     * Returns the appropriate {@link Response} for the specified input.
     *
     * @param input the user input to process.
     * @return the response from the chat bot.
     */
    public Response getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(taskList, storage);
        return new Response(c.isExit(), c.feedback());
    }
}

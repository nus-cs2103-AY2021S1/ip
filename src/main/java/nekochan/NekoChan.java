package nekochan;

import nekochan.command.Command;
import nekochan.command.Response;
import nekochan.exceptions.NekoStorageException;
import nekochan.parser.Parser;
import nekochan.storage.Storage;
import nekochan.task.TaskList;

/**
 * The {@code NekoChan} class controls the logic of the chat bot.
 */
public class NekoChan {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of the chat bot logic.
     * Uses the default save location.
     *
     * @param useHistory true if the local save file at the default save location should be loaded.
     */
    public NekoChan(boolean useHistory) {
        this(Storage.FILE_PATH, useHistory);
    }

    /**
     * Constructs an instance of the chat bot logic.
     *
     * @param filePath the file path to store the local save file.
     * @param useHistory true if the local save file should be loaded.
     * @throws NekoStorageException if the file at the specified {@code filePath} could not be loaded.
     */
    public NekoChan(String filePath, boolean useHistory) throws NekoStorageException {
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

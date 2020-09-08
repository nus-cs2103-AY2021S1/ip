package nekochan;

import nekochan.command.Command;
import nekochan.command.Response;
import nekochan.exceptions.NekoStorageException;
import nekochan.model.NekoHistory;
import nekochan.model.task.TaskList;
import nekochan.parser.Parser;
import nekochan.storage.Storage;

/**
 * The {@code NekoChan} class controls the logic of the chat bot.
 */
public class NekoChan {

    private NekoHistory history;
    private Storage storage;

    /**
     * Constructs an instance of the chat bot logic.
     * Uses the default save location.
     *
     * @param useSave true if the local save file at the default save location should be loaded.
     */
    public NekoChan(boolean useSave) {
        this(Storage.FILE_PATH, useSave);
    }

    /**
     * Constructs an instance of the chat bot logic.
     *
     * @param filePath the file path to store the local save file.
     * @param useSave true if the local save file should be loaded.
     * @throws NekoStorageException if the file at the specified {@code filePath} could not be loaded.
     */
    public NekoChan(String filePath, boolean useSave) throws NekoStorageException {
        TaskList taskList;
        if (useSave) {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } else {
            storage = new Storage(filePath);
            taskList = new TaskList();
        }
        history = new NekoHistory(taskList);
    }

    /**
     * Returns the appropriate {@link Response} for the specified input.
     *
     * @param input the user input to process.
     * @return the response from the chat bot.
     */
    public Response getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(history, storage);
        return c.feedback();
    }
}

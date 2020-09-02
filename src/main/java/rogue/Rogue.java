package rogue;

import rogue.logic.Report;
import rogue.logic.directives.Executable;
import rogue.logic.exceptions.IncorrectArgumentException;
import rogue.logic.parser.Parser;
import rogue.logic.parser.exceptions.UnknownCommandException;
import rogue.model.task.TaskList;
import rogue.storage.Storage;
import rogue.storage.exceptions.StorageException;
import rogue.ui.Ui;

/**
 * The chat-bot {@code Rogue}. Consists of three main elements: the {@code Storage},
 * the {@code TaskList}, and the {@code Ui}.
 */
public class Rogue {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs {@code Rogue}.
     *
     * Loads data from the file at the given path.
     *
     * The data is used to recreate the tasks if it is successfully parsed.
     * Otherwise, an empty {@code TaskList} is used instead.
     *
     * @param filePath The relative or absolute path where persistent information is stored.
     */
    public Rogue(String filePath) {
        storage = Storage.init(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
        ui = new Ui();
    }

    /**
     * Parses a user input.
     *
     * A valid input is executed by {@code Rogue} and an appropriate response
     * will be generated.
     *
     * An invalid input or a failed execution of valid input will result in
     * an error message.
     *
     * @param input The user input to parse.
     * @return A response from {@code Rogue}
     */
    public String getResponse(String input) {
        try {
            Executable e = Parser.createExe(input);
            Report r = e.execute(storage, tasks, ui);
            return r.toString();
        } catch (UnknownCommandException | IncorrectArgumentException | StorageException e) {
            return e.getMessage();
        }
    }
}

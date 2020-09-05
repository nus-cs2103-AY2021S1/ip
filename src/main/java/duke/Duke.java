package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.core.Parser;
import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.CommandNotFoundException;
import duke.handle.LoadingException;
import duke.handle.TaskNotFoundException;

/**
 * The Duke class represents a duke bot that can interact with
 * the user and help the user to manage their tasks, which can
 * help to store the tasks entered by the user into a local record, add
 * the tasks, remove the tasks, read the tasks in the record and
 * present them to the user, and help to
 * search for a specific task for the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Takes in the path of the local record, and creates a duke bot to interact with
     * the user.
     */
    public Duke() throws FileNotFoundException, LoadingException, IOException {
        assert FILE_PATH != null: "the file path should not be null";

        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.readRecord());
        } catch (FileNotFoundException fileNotFoundException) {
            taskList = new TaskList();

            storage.writeRecord(taskList);

            throw fileNotFoundException;
        } catch (LoadingException loadingException) {
            taskList = new TaskList();

            storage.writeRecord(taskList);

            throw loadingException;
        }
    }

    public Result getResponse(String command) {
        try {
            Command parsedCommand = Parser.parseCommand(command);
            assert parsedCommand != null : "the parsed command should not be null";
            return parsedCommand.excecute(taskList, ui, storage);
        } catch (CommandNotFoundException commandNotFoundException) {
            //System.out.println(commandNotFoundexException.getMessage());
            return new Result(ui.handleException(commandNotFoundException), true);
        } catch (TaskNotFoundException taskNotFoundException) {
            return new Result(ui.handleException(taskNotFoundException), true);
        } catch (IOException ioException) {
            return new Result(ui.handleException(ioException), true);
        }
    }

    public Ui getUi() {
        return ui;
    }
}
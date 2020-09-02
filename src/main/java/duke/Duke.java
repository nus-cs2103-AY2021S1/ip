package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.core.Parser;
import duke.core.Result;
import duke.core.Ui;
import duke.core.Storage;
import duke.core.TaskList;
import duke.handle.CommandNotFoundException;
import duke.handle.TaskNotFoundException;
import duke.handle.LoadingException;
import javafx.scene.image.Image;

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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Takes in the path of the local record, and creates a duke bot to interact with
     * the user.
     */
    public Duke() throws FileNotFoundException, LoadingException {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.readRecord());
        } catch (FileNotFoundException fileNotFoundException) {
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }
            throw fileNotFoundException;
        } catch (LoadingException loadingException) {
            //ui.handle(loadingException);
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }
            throw loadingException;
        }
    }

    public Result getResponse(String command) {
        try {
            Command parsedCommand = Parser.parseCommand(command);
            return parsedCommand.excecute(taskList, ui, storage);
            //isContinuing = parsedCommand.isContinuing();
        } catch (CommandNotFoundException commandNotFoundexException) {
            //System.out.println(commandNotFoundexException.getMessage());
            return new Result(ui.handle(commandNotFoundexException), true);
        } catch (TaskNotFoundException taskNotFoundException) {
            return new Result(ui.handle(taskNotFoundException), true);
        } catch (IOException ioException) {
            return new Result(ui.handle(ioException), true);
        }

        //Command parsedCommand = Parser.parseCommand(command);
        //return parsedCommand.excecute(taskList, ui, storage);

        //return "Smith heard: " + string;
    }

    public Ui getUi() {
        return ui;
    }
}
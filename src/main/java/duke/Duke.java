package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Platform;


/**
 * Represents the chat bot Duke.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;

    /**
     * Represents the chat bot duke.
     */
    public Duke() {
        storage = Storage.dbInstance();
        taskList = storage.getTaskListFromDatabase();
    }

    /**
     * Starts up the chat bot by welcoming and listing out saved tasks. Prompts the user to enter
     * valid commands until the bye command is issued.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = Parser.parse(input);
            if (command.getExitStatus() == true) {
                Platform.exit();
            }
            response = command.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please enter a valid task index!";
        }
        return response;
    }
}

package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.support.Parser;
import duke.support.Storage;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Duke is a small educational project which acts
 * based on user input.
 * @author Xia Liyi.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a {@code Duke} with a storage and a filled task list.
     */
    public Duke() {
        storage = new Storage("duke.txt");
        taskList = new TaskList(storage.getTaskList());
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String res = cmd.run(taskList, storage);
            if (cmd.isBye()) {
                Platform.exit();
                System.exit(0);
            }
            return res;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


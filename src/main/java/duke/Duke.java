package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.task.TaskList;


/**
 * Chat bot to keep track of tasks.
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Creates Duke object.
     */
    public Duke() {

        Path filePath = Paths.get("data", "duke.txt");
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.setLoadingErrorMessage();
            taskList = new TaskList();
        }
    }

    /**
     * Returns response message of Chat Bot according to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            return ui.getResponseMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

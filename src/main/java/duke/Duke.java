package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // constructor
    public Duke() {
        this("data", "./data/duke.txt");
    }

    public Duke(String dataDirectory, String filePath) {
        ui = new Ui();
        storage = new Storage(dataDirectory, filePath);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.format(e.getMessage());
        }
    }

    public Ui getUi() {
        return ui;
    }
}


package duke;

import java.io.IOException;

import duke.tasks.Task;
import duke.tool.Parser;
import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * The Duke server which can mange tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Constructs the Duke server.
     *
     * @param filePath the path where the file is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Gets duke's response.
     */
    public String getResponse(String input) {
        Task c = Parser.parse(input, tasks);
        return c.execute(tasks, ui, storage);
    }
}

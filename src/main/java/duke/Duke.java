package duke;

import java.util.ArrayList;
import java.util.List;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws Exception {
        this.ui.initialise(tasks, storage);
    }

    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("data/duke.ser");
        duke.run();
    }
}

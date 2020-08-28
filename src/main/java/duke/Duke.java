package duke;

import java.io.IOException;

public class Duke {
    private Storage store;
    private TaskList taskList;
    private Ui ui;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.store = new Storage(filePath);
        try {
            this.taskList = new TaskList(store.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, store);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("../data/taskmanager.txt").run();
    }
}
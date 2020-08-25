package duke;

import duke.command.Command;
import duke.component.*;

/**
 * Chat bot that can manage your tasks!
 * @author Tian Fang
 * @version v0.1
 * @since 2020-08-18
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initializes Duke with the given file path
     * @param filePath the file path used to initialize Duke
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readList());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.giveResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String path = "data/tasks.txt";
        new Duke(path).run();
    }
}

package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Chat bot to keep track of tasks.
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingErrorMessage();
            taskList = new TaskList();
        }
    }

    private void run() {

        ui.greet();

        boolean isExit = false;

        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.wrapMessage(e.toString());
            }

        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
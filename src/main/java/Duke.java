import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    public Duke(String folderPath, String filePath) {
        this.storage = new Storage(folderPath, filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke("data","data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }
}

package duke.duke;

import java.io.File;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

public class Duke {

    private Storage store;
    private TaskList tasks;
    private UI ui;

    public Duke(String directoryPath, String filePath) {
        ui = new UI();
        store = new Storage(directoryPath, filePath);
        File loadFile = store.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, store);
            isExit = command.isExit();
            if (!isExit) {
                ui.displayBlankLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("./data", "duke.txt");
        duke.run();
    }

}

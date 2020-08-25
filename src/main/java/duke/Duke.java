package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        
        while (!isExit) {
            try {
                String fullCommand = ui.requestCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    // main
    public static void main(String[] args) {
        // file path
        String FILEPATH = System.getProperty("user.dir")
                + (System.getProperty("user.dir").endsWith("text-ui-test")
                ? "/test_data/duke.txt"
                : "/data/duke.txt");
        
        new Duke(FILEPATH).run();
    }
}

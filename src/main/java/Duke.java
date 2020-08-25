import duke.resource.Parser;
import duke.resource.TaskList;
import duke.util.DukeException;

import duke.Command;
import duke.Storage;
import duke.Ui;

public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ui.welcome();
        try {
            tasks = TaskList.parse(this.storage.load());
            ui.loaded(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    public void run() {
        ui.start();
        boolean terminate = false;
        while (!terminate) {
            try {
                String command = ui.read();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                terminate = c.terminate();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    public static void main(String[] args){
        // change the filePath below to save elsewhere
        new Duke("./src/main/data/duke.txt").run();
    }
}

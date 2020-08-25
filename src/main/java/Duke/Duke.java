package Duke;

import Duke.Exceptions.DukeException;
import Duke.Tasks.Task;
import Duke.Tool.Parser;
import Duke.Tool.Storage;
import Duke.Tool.TaskList;

import java.io.IOException;

/**
 * The Duke server which can mange tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of the Duke server.
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

    /**
     * Run the Duke server.
     * @throws DukeException Exceptions throws in Duke.
     */
    public void run() throws DukeException {
        ui.showLogo();
        ui.showGreeting();
        boolean continueOperate = true;
        while (continueOperate) {
            String order = ui.getOrder();
            Task c = Parser.parse(order, tasks);
            c.excute(tasks, ui, storage);
            continueOperate = !c.isExit;
        }
        ui.showGoodbye();
    }

        public static void main(String[] args) throws DukeException {
            new Duke("data/tasks.txt").run();
        }
}

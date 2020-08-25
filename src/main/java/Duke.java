import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.parser.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        Boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage);
                c.printFeedback(ui);
                isExit = c.isExit();
            } catch (IncompleteDukeCommandException e) {
                ui.formattedPrint(ui.prependIndent("Something went wrong, but I'm not sure what...", 1));
            } catch (DukeException e) {
                ui.formattedPrint(ui.prependIndent(e.getMessage(), 1));
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Storage.FILE_PATH).run();
    }
}

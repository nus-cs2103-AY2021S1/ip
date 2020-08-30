import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Clui;
import duke.ui.Ui;

public class DukeCli {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    DukeCli(String filePath) {
        ui = new Clui();
        storage = new Storage(filePath);
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
                ui.print("Something went wrong, but I'm not sure what...");
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new DukeCli(Storage.FILE_PATH).run();
    }
}

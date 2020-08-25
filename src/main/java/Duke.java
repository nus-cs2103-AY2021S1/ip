import duke.command.Command;
import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

//TODO: Stretch Goals: Level 8- Use date related command
public class Duke {

    private static final String DATA_FILE = "data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        while (!Command.isTerminated) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(taskList, ui);
            } catch (DukeException de) {
                ui.printError(de);
            }
        }
    }

    public void terminate() {
        storage.saveToFile(taskList.export());
        ui.showExit();
    }


    public static void main(String[] args) {
        Duke duke = new Duke(DATA_FILE);
        duke.run();
        duke.terminate();
    }



}

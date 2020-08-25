import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String directoryPath, String dataFilePath) {
        ui = new Ui();
        storage = new Storage(directoryPath, dataFilePath);
        ArrayList<String> lines = storage.readFile();
        parser = new Parser();
        ArrayList<Task> lst = parser.parseSavedTaskList(lines);
        tasks = new TaskList(lst);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        try {
            while (!isExit) {
                String userInput = ui.readCommand();
                Command c = parser.parse(userInput, tasks.lst);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
        } catch (DukeException | DateTimeParseException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data", "src/main/data/data.txt").run();
    }
}

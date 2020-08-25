import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    // path of the data to be stored
    private static final Path path = Paths.get(".", "data", "duke.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
            tasks = new TaskList(storage.getCurrentTasks());
        } catch (DukeException e) {
            ui.errorMessage(e.getMessage());
        }
    }

    public void runDuke() {
        ui.welcomeMessage(tasks.toString());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.errorMessage(e.getMessage());
            }
        }

    }


    public static void main(String[] args) {
        new Duke().runDuke();
    }
}

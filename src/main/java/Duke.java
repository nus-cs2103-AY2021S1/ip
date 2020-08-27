import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList tasks;
    private Storage storage;

    Duke() {
        ui = new Ui();
        parser = new Parser();
        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "data", "tasks.txt");
        storage = Storage.setup(path);
        tasks = storage.readData();
    }

    void exit() {
        storage.update(tasks);
        ui.exit();
        System.exit(0);
    }
    
    public void run() {
        try {
            ui.displayWelcome();
            boolean isExit = false;
            while (!isExit) {
                String userInput = ui.readInput();
                Command c = parser.parse(userInput);
                c.execute(tasks, ui);
                isExit = c.isExit();
            }
            exit();
        } catch (DukeException e) {
            ui.displayError(e.toString());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

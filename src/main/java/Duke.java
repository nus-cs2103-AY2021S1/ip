import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    public Duke() {
        ui = new UI();
        tasks = new TaskList();
        String directory = System.getProperty("user.dir");
        Path filePath = Paths.get(directory, "data", "data.txt");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.displayError(e);
        }
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            return parser.run(tasks, storage, ui);
        } catch (Exception e) {
            return "Unknown command";
        }
    }
}

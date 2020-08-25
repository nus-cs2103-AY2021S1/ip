import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

/**
 * Main class for the Duke CLI task-tracking application.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the instance attributes: storage, tasks, ui.
     *
     * @param filePath File path of storage data.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlankTaskException e) {
            ui.displayOutput(e.getMessage());
        } catch (IOException e) {
            ui.displayOutput(Ui.MESSAGE_ERROR_IO);
        }
    }

    /**
     * Drives the application and all of the underlying processes.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c;
            try {
                c = Parser.parse(fullCommand);
            } catch (MissingDelimiterException | MissingDateTimeException | InvalidCommandException e) {
                ui.displayOutput(e.getMessage());
                continue;
            } catch (DateTimeParseException e) {
                ui.displayOutput(Ui.MESSAGE_WRONG_FORMAT);
                continue;
            } catch (IndexOutOfBoundsException e) {
                ui.displayOutput(Ui.MESSAGE_TASK_ID_MISSING);
                continue;
            }
            try {
                c.execute(tasks, ui, storage);
            } catch (BlankTaskException e) {
                ui.displayOutput(e.getMessage());
            } catch (IOException e) {
                ui.displayOutput(Ui.MESSAGE_ERROR_IO);
            } catch (IndexOutOfBoundsException e) {
                ui.displayOutput(Ui.MESSAGE_INVALID_ID);
            }
            isExit = c.isExit();
        }
    }

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Path dataPath = Paths.get("data", "duke.txt");
        new Duke(dataPath).run();
    }
}

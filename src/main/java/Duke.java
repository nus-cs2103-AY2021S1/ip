import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;

/**
 * Main class for the Duke CLI task-tracking application.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the instance attributes: storage, tasks, ui.
     */
    public Duke() {
        ui = new Ui();
        Path dataPath = Paths.get("data", "duke.txt");
        storage = new Storage(dataPath);

        try {
            tasks = new TaskList(storage.load());
        } catch (BlankTaskException | InvalidCommandException e) {
            ui.displayOutput(e.getMessage());
        } catch (IOException e) {
            ui.displayOutput(Ui.MESSAGE_ERROR_IO);
        }
    }

    String getResponse(String input) {
        Command c;

        try {
            c = Parser.parse(input);
            assert c != null : "The command cannot be null";
        } catch (MissingDelimiterException | MissingDateTimeException | InvalidCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_WRONG_FORMAT;
        } catch (IndexOutOfBoundsException e) {
            return Ui.MESSAGE_TASK_ID_MISSING;
        }

        String response;

        try {
            response = c.execute(tasks, ui, storage);
            assert !response.isBlank() : "Response should not be empty";
        } catch (BlankTaskException e) {
            return e.getMessage();
        } catch (IOException e) {
            return Ui.MESSAGE_ERROR_IO;
        } catch (IndexOutOfBoundsException e) {
            return Ui.MESSAGE_INVALID_ID;
        }

        if (response.equals("exit")) {
            Platform.exit();
        }

        return response;
    }
}

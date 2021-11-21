import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;

/**
 * Main class for the Mario CLI task-tracking application.
 */
public class Mario {

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialises the instance attributes: storage, tasks, ui.
     */
    public Mario() {
        ui = new Ui();
        Path dataPath = Paths.get("data", "mario.txt");
        Path aliasMapFilePath = Paths.get("data", "keymap.txt");
        storage = new Storage(dataPath, aliasMapFilePath);

        try {
            tasks = new TaskList(storage.loadTaskList());
            parser = new Parser(storage.loadAliasMapping());
        } catch (BlankTaskException | InvalidCommandException e) {
            ui.displayOutput(e.getMessage());
        } catch (IOException e) {
            ui.displayOutput(Ui.MESSAGE_ERROR_IO);
        }
    }

    String getResponse(String input) {
        Command c;

        try {
            c = parser.parse(input);
            assert c != null : "The command cannot be null";
        } catch (MissingDelimiterException | MissingDateTimeException
                | InvalidCommandException | AliasNotAllowedException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_WRONG_FORMAT;
        } catch (IndexOutOfBoundsException e) {
            return Ui.MESSAGE_TASK_ID_MISSING;
        } catch (NumberFormatException e){
            return Ui.MESSAGE_NOT_A_NUMBER;
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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class for the Duke CLI task-tracking application.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    String getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
        } catch (MissingDelimiterException | MissingDateTimeException | InvalidCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_WRONG_FORMAT;
        } catch (IndexOutOfBoundsException e) {
            return Ui.MESSAGE_TASK_ID_MISSING;
        }
        try {
            String response=c.execute(tasks, ui, storage);
            if (response.equals("exit")){
                Platform.exit();
            }
            return response;
        } catch (BlankTaskException e) {
            return e.getMessage();
        } catch (IOException e) {
            return Ui.MESSAGE_ERROR_IO;
        } catch (IndexOutOfBoundsException e) {
            return Ui.MESSAGE_INVALID_ID;
        }
    }

    /**
     * Initialises the instance attributes: storage, tasks, ui.
     */
    public Duke(){
        ui = new Ui();
        Path dataPath = Paths.get("data", "duke.txt");
        storage = new Storage(dataPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlankTaskException e) {
            ui.displayOutput(e.getMessage());
        } catch (IOException e) {
            ui.displayOutput(Ui.MESSAGE_ERROR_IO);
        }
    }
}

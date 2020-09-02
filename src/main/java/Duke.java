import com.sun.prism.paint.ImagePattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

/**
 * Represents a Duke chatbot that can store, delete, mark tasks as done and display them.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // needed for launcher to run
    public Duke() {
    }

    /**
     * Creates a Duke object that loads information from specified filePath.
     * @param filePath The text file which Duke loads information from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Displays a welcome message and runs the chatbot,
     * continuously receiving user input and executing them accordingly.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputData = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(inputData);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs a Duke object with a file at filePath of "data/duke.txt".
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke dukeObj = new Duke("data/duke.txt");
        dukeObj.run();
    }
}

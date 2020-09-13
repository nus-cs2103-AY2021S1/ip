import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Main body of application Duke
 */

public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    // javafx
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Constructor
     */
    public Duke() {
        this.start();
    }


    /**
     * Initialises the application
     */
    private void start() {
        this.storage = new Storage("Data.txt");
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(storage, ui, tasks);
        this.storage.init();
        storage.importSavedDataToList(tasks.get());
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws DukeException {

        return this.parser.processInput(input);
    }


}

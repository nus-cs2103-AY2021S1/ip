package duke.core;

import java.io.File;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Duke programme implements an application that works as task tracker productivity tool.
 * It can track things to do, deadlines and events as well as their status - done or not yet done.
 * Users input text commands, and the programme will parse it and generate an appropriate response.
 * The programme terminates after the Ui object within is deactivated
 * @see Ui
 */
public class Duke {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String DEFAULT_FILE_PATH = PROJECT_ROOT + File.separator + "data" + File.separator + "saveData.txt";
    private static final String DEFAULT_DIRECTORY = PROJECT_ROOT + File.separator + "data";

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Public constructor to create a Duke object.
     * Required for JavaFX to instantiate the object.
     */
    public Duke() {
        String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy pardner!! I'm\n" + logo);

        System.out.println("What can I get yer for?");

        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH, DEFAULT_DIRECTORY);
        taskList = new TaskList();
        taskList.loadTasks(storage.loadData());
        parser = new Parser();
    }

    String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            String reply = command.execute(taskList, storage, ui);
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

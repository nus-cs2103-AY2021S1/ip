package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.storage.Storage;
import duke.util.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant Chatbot that helps a user keep track of various things.
 */
public class Duke {

    /** list of tasks to keep track of */
    private TaskList taskList;
    /** storage object handling saving and editing of the save file in the hard drive */
    private Storage storage;
    /** ui object in charge of direct interaction with the user */
    private Ui ui;



    /**
     * Standard constructor initialising the Duke bot.
     * It will attempt to search for a pre-saved save file of a previous task list.
     * If found, it loads up that task list.
     * Otherwise it creates its own which will be saved and able to be opened by future Duke bots.
     */
    public Duke() {
        storage = Storage.init();
        taskList = storage.readStoredData();
        ui = new UiDefault();
    }

    protected Duke(Ui ui) {
        storage = storage.init();
        taskList = storage.readStoredData();
        this.ui = ui;
    }

    /**
     * Initialises and runs the Duke chat bot. Does not require input arguments.
     * @param args Input arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the duke bot.
     * It continuously queries the ui for the next user response and responds appropriately.
     */
    public void run() {
        ui.startup();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getInput();
                Command command = CommandParser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
                ui.exit();
            } catch (DukeException e) {
                ui.outputMessage(e.getMessage());
            }
        }

    }



}




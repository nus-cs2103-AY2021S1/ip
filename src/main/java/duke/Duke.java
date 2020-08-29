package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * Main class to run Ui object and listen for user input
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        ui.showWelcome();
        return "Duke heard: " + input;
    }

    public void run() throws Exception {
        this.ui.initialise(tasks, storage);
    }

    public static void main(String[] args) throws Exception {
        // remember to change filepath to "../../../data/duke.ser" during jar build
        Duke duke = new Duke("data/duke.ser");
        duke.run();
    }
}

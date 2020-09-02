package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * The Duke program represents a person assistant
 * chatbot that helps the user manage tasks and store them
 * in a list according to the user input.
 * 
 * @author York Tat
 * @version 1.0
 * @since 2020-08-14
 */
public class Duke {
    
    private Storage store;
    private TaskList taskList;
    private Ui ui;
    
    public Duke() {
    }

    /**
     * Creates a new Duke chatbot that saves and loads tasks from the given filePath
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.store = new Storage(filePath);
        try {
            this.taskList = new TaskList(store.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    
    /**
     * Runs the chatbot until an exit command is issued.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, store);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }
    
    public String showWelcomeMessage() {
        return this.ui.greet();
    }
    
    public String getResponse(String input) {
        return run(input);
    }
}
import java.io.IOException;
import java.util.ArrayList;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



/**
 * <h1> DUKE CLASS </h1>
 * The Duke Class contains the method to
 * initialize the DukeChat bot. Different commands will
 * result in different course of action.
 *
 * Currently Duke only supports Todo, Deadline, Event, Done, Delete, List, Find
 * commands as a Task Tracker.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/photo3.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/photo3.png"));

    /**
     * Instantiate a Duke Object with the filepath as that name
     * of the file which the lists will be saved to.
     *
     */
    public Duke() {
        this.taskList = TaskList.createTaskList();
        this.storage = Storage.createDukeFile("Saved");
        try {
            ArrayList<String> taskHistory = this.storage.loadFile();
            Parser.processOldTasks(taskHistory, this.taskList);
        } catch (IOException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
    }


    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //String cmd = sc.nextLine().trim().toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        if (!input.equals("bye") && input.length() != 0) {
            try {
                stringBuilder.append(Parser.process(input, this.taskList, this.storage));
            } catch (DukeException e) {
                stringBuilder.append(Ui.showError(e.getMessage()));
            }
        } else {
            try {
                stringBuilder.append(Ui.showSaving(this.storage.saveToFile()));
            } catch (IOException e) {
                stringBuilder.append(Ui.showError(e.getMessage()));
            }
            stringBuilder.append(Ui.goodbyeMessage());
        }
        return stringBuilder.toString();
    }

}

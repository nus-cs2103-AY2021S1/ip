package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.inputoutput.InputOutput;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;


/** Main class where the program is run. */
public class Duke {

    private InputOutput inputOutput;
    private TaskList taskList;
    private Storage storage;

    //    private ScrollPane scrollPane;
    //    private VBox dialogContainer;
    //    private TextField userInput;
    //    private Button sendButton;
    //    private Scene scene;

    /** Constructs a Duke object. */
    public Duke() {
        this.inputOutput = new InputOutput();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    //    @Override
    //    public void start(Stage stage) throws Exception {
    //
    //    }
    //
    //    /**
    //     * Iteration 1:
    //     * Creates a label with the specified text and adds it to the dialog container.
    //     *
    //     * @param text String containing text to add
    //     * @return a label with the specified text that has word wrap enabled.
    //     */
    //    private Label getDialogLabel(String text) {
    //        Label textToAdd = new Label(text);
    //        textToAdd.setWrapText(true);
    //
    //        return textToAdd;
    //    }

    /** Main driver method. */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /** Runs the program until termination. */
    public void run() {
        inputOutput.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = inputOutput.readCommand();
            try {
                Command command = Parser.parse(input);
                CommandResult result = command.execute(taskList);
                inputOutput.show(result.getFeedbackToUser());
                storage.save(taskList);
                isExit = result.isExit();
            } catch (Exception ex) {
                inputOutput.show("\t " + ex.getMessage());
            }
        }
        System.exit(0);
    }
}

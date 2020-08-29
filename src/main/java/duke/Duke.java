package duke;

import duke.exception.DukeException;
import duke.tool.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Represents the duke system.
 */
public class Duke extends Application {

    /** Storage of the system */
    private final Storage storage;

    /** Task list that stores tasks */
    private TaskList tasks;

    /** User interface to interact with user */
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Creates new Duke chat bot from given storage path.
     *
     * @param filePath Data file path.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            ui = new Ui(storage,tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot open data file");
        }
    }

    public Duke() throws DukeException {
        this("data/duke.txt");
    }
//
//    /**
//     * Runs the Duke system.
//     */
//    public void run() {
//
//        //Start greet
//        ui.showLogo();
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//
//        //Run until see the exit command
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.excute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.printLog(e.getMessage());
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }



    @Override
    public void start(Stage stage) {
        //Setting up required components
        ui.process(stage);
    }
}

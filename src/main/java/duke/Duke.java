package duke;

import duke.command.UserCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();

    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./taskdata.txt");
        tasks = storage.load();
    }

//    /**
//     * Executes the Duke program.
//     */
//    public void run() {
//        ui.greetUser();
//        boolean isExit = false;
//        while (isExit != true) {
//            try {
//                String input = ui.readCommand();
//                UserCommand command = Parser.parse(input);
//                isExit = command.isExit;
//                command.execute(tasks, ui);
//                storage.save(tasks);
//            } catch (DukeException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }

    public String getResponse(String userinput) {
        try {
            UserCommand command = Parser.parse(userinput);
            String s = command.execute(tasks, ui);
            storage.save(tasks);
            return s;
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

}




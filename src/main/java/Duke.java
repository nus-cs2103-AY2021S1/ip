import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Main class for Duke program.
 */
public class Duke {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaMith.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Entry point of the duke program. Read from storage and creates a UI class instance.
     * Serves as intermediate between the UI and parser.
     *
     * @param args
     * @throws DukeException
     */
//    public static void main(String[] args) throws DukeException {
//        TaskList tasks = Storage.read();
//        assert tasks != null : "error initializing tasks";
//        UI ui = new UI();
//        assert ui != null : "error initializing UI";
//        ui.welcome(tasks);
//        String input = ui.getInput();
//        Parser parser = new Parser(tasks);
//        assert ui != null : "error initializing parser";
//        while (!input.equals("bye")) {
//            if (input.isEmpty()) {
//                input = ui.getInput();
//                continue;
//            }
//            parser.parse(input);
//
//            input = ui.getInput();
//        }
//        ui.bye();
//    }

    TaskList tasks;
    Parser parser;

    public Duke(){

        tasks = Storage.read();
        assert tasks != null : "error initializing tasks";
        UI ui = new UI();
        assert ui != null : "error initializing UI";
        ui.init();
        parser = new Parser(tasks);
    }

    public String welcome() {
        return parser.welcome();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}

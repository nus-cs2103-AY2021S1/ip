package duke.main;

import java.util.Scanner;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

/**
 * Duke is a chatbot that allows crud operations, and can add three different types of task,
 * namely todo, events, and deadlines
 */
//public class Duke extends Application {
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Run command to start Duke
     *
     * @param sc Scanner for input
     */
    public void run(Scanner sc) {
        this.ui.showWelcome();
        this.ui.checkCommands(sc, this.tasks);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new Duke("./data/tasks.txt").run(sc);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

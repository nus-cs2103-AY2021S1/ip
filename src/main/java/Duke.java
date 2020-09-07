import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;




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

    /**
     * Duke constructor takes in file path to setup duke
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        assert filePath.contains(".txt"); //enure that correct file type is passed
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the Duke instance
     */
    public void run() {
        ui.welcome();
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Command.parse(command);
                c.execute(tasks, ui, storage);
                exit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();;
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String command = input;
        try {
            Command c = Command.parse(command);
            return "Duke heard: " + c.execute(tasks, ui, storage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Duke exception: " + e.getMessage();
        }
    }
}
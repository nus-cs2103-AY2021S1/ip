import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * main class
 */
public class Duke {

    // Initialise my variables for duke backend data handling
    Ui myDukeBot = new Ui();
    Storage myStorage = new Storage();
    Parser myParser = new Parser();
    TaskList myTaskList = new TaskList();

    // Added 2 image items for use later in duke

    private Image user = new Image(this.getClass().getResourceAsStream("/images/SeanDuke.JPG"));

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/JARVIS.JPG"));

    public String getResponse(String input) {
        String response = myParser.listenerForUI(myTaskList, myDukeBot,input);
        myStorage.updateDirectory(myTaskList);
        return response;
    }


}

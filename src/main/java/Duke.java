import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * A personalized chat bot that is designed to help users manage their schedule. There are three
 * different types of listings - ToDos, Deadlines and Events.
 *
 * @author Gabriel Sim
 * @version 1.0
 * @since 2020-09-02
 */

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Poring.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Poro.jpg"));
    private Bot bot = new Bot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iteration 3: Creates two dialog boxes, one echoing user input and the other containing Duke's
     * reply and then appends them to the dialog container. Clears the user input after processing.
     * Dialog boxes are now alternating
     */

    /**
     * You should have your own function to generate a response to user input. Replace this stub with
     * your completed method.
     */
    String getResponse(String input) { //where to get response
        String output = bot.serve(input);
        if (input.equals("exit")) {
            Platform.exit();
        }
        return "Duke heard: " + output;
    }

//  public static void main(String[] args) {
//
//    Bot bot = new Bot();
//    bot.serve();
//
//  }

}


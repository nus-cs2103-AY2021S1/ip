import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A personalized chat bot that is designed to help users manage their schedule. There are three
 * different types of listings - ToDos, Deadlines and Events.
 *
 * @author Gabriel Sim
 * @version 1.0
 * @since 2020-09-02
 */

public class Duke extends Application {

  @Override
  public void start(Stage stage) {
    Label helloWorld = new Label("Hello World!"); // Creating a new Label control
    Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

    stage.setScene(scene); // Setting the stage to show our screen
    stage.show(); // Render the stage.
  }

//  public static void main(String[] args) {
//
//    Bot bot = new Bot();
//    bot.serve();
//
//  }

}


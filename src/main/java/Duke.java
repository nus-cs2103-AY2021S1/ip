import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.manager.Parser;

/**
 * Represents the Duke chat bot.
 */
public class Duke extends Application {

    private void printGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Runs the Duke bot by printing the greeting message,
     * instantiating a parser to handle user input
     * and printing the goodbye message upon exit.
     */
    public void run() {
        printGreeting();
        new Parser().handleUserInput();
        printGoodbye();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Main method that drives the running of the Duke bot.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

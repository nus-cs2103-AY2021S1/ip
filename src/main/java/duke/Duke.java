package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * <h1>Duke IP Project</h1>
 *
 * <p>A simple task managing chatbot. Functionalities include saving tasks, completing tasks, and </p>
 */
public class Duke extends Application {

    private Button sendButton;
    private TextField userInputTextField;
    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private Scene scene;

    /**
     * Entry point of Duke. Simulates and main application loop.
     * @param args
     */
    public static void main(String[] args) {
        Ui duke = new Ui();
        duke.start();
        Scanner sc = new Scanner(System.in);

        /* Initial setting of password */
        while (!duke.checkAuth()) {
            System.out.println("Please set a user password for auth.");
            String p1 = sc.nextLine();
            System.out.println("Please confirm password");
            String p2 = sc.nextLine();
            if (p1.trim().equals(p2.trim())) {
                duke.setPw(p1.trim());
            }
        }

        /* Main App Loop */
        int x = 1;
        //Loop until exit command given
        while (x > 0) {
            if (sc.hasNext()) {
                x = duke.takeInputAndReturn(sc.nextLine());
            } else {
                duke.end();
            }
        }
        if (x < 0) {
            System.out.println("Fatal system error uncaught in main logic");
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Formats the stage frame
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(400.0);
        primaryStage.setMinHeight(600.0);

        // Create button with text "Send" as the send button
        sendButton = new Button();
        sendButton.setText("Send");

        // Create the scroll pane
        scrollPane = new ScrollPane();

        // Create the text input
        userInputTextField = new TextField();

        // Create the vertical box pane for the dialogue container
        dialogueContainer = new VBox();

        // Add the content to the scroll pane for a scrollable content
        scrollPane.setContent(dialogueContainer);

        // Creates the main layout pane
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInputTextField, sendButton);

        // Sets the main layout as a scene
        Scene scene = new Scene(mainLayout, 300 ,500);

        // Sets the scene for the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
        sendButton.setPrefWidth(55.0);


        // Set send button event handlers
        sendButton.setOnMouseClicked(event -> {
            dialogueContainer.getChildren().add(getDialogLabel(userInputTextField.getText()));
            userInputTextField.clear();
        });


        // Create the scroll pane
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);


        // Create the text input
        userInputTextField = new TextField();
        userInputTextField.setPrefWidth(325.0);


        // Sets the action on text change
        userInputTextField.setOnAction(event -> {
            dialogueContainer.getChildren().add(getDialogLabel(userInputTextField.getText()));
            userInputTextField.clear();
        });


        // Create the vertical box pane for the dialogue container
        dialogueContainer = new VBox();
        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Makes the scroll
        dialogueContainer.heightProperty().addListener(observable -> {
            scrollPane.setVvalue(1.0);
        });


        // Add the content to the scroll pane for a scrollable content
        scrollPane.setContent(dialogueContainer);


        // Creates the main layout pane
        AnchorPane mainLayout = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInputTextField , 1.0);
        AnchorPane.setBottomAnchor(userInputTextField, 1.0);
        mainLayout.getChildren().addAll(scrollPane, userInputTextField, sendButton);


        // Sets the main layout as a scene
        Scene scene = new Scene(mainLayout, 300 ,500);


        // Sets the scene for the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

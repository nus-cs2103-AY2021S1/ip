import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import dukeclass.Parser;
import dukeclass.Storage;
import dukeclass.TaskList;
import dukeclass.Ui;

/**
 * Encapsulates a chat robot that you can chat with to set tasks for yourself.
 */
public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @Override
    public void start(Stage stage) {
        // set title
        stage.setTitle("DUKE THE ANGRY ROBOT");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // set stage


        // set stuff in scene
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // set scene
        scene = new Scene(mainLayout);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();

        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * change access modifier to private after packaging it
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


    /**
     * Duke to be started, create files for storage, run commands and write to files.
     */
    public static void startDuke() {
        TaskList taskList = new TaskList();

        File f = Storage.createFile();

        System.out.println(Ui.welcomeMessage());

        try {
            Storage.readFromFile(f, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Loading done");


        try {
            Parser.parseCommands(taskList);

        } catch (Exception e) {
            System.out.println(Ui.unknownInputErrorMessage(e));
        }


        try {
            Storage.writeToFile(f, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println(Ui.endMessage());


    }

    /**
     * Main method to start Duke
     */
    public static void main(String[] args) throws FileNotFoundException {

        Duke.startDuke();

    }


}


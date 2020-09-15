package godfather.ui;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicalUi extends javafx.application.Application implements Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @Override
    public void start(Stage stage) throws Exception {
        //=============  step1: set up required components =================
        // container for the content for the chat to scroll:
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);
        this.userInput = new TextField();
        this.sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        // add these as children nodes:
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        this.scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        //============ Step 2. Formatting the window to look as expected =============
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // no scroll for horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // scroll for vertical only
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        // =========== Step3: Add functionality to handle User Input ====================
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }
    /**
     * Iteration 1: Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     *
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
    /**
     * Iteration 2: Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, this.user),
                                             DialogBox.getDukeDialog(dukeText, this.duke));
        userInput.clear();
    }
    private String getResponse(String text) {
        return "Duke heard: " + text;
    }
    @Override
    public void greet() {
    }
    @Override
    public String readCommand(Scanner sc) {
        return null;
    }
    @Override
    public void bidFarewell() {
    }
    @Override
    public void display(ArrayList<String> lines) {
    }
    @Override
    public void displayError(String message) {
    }
}

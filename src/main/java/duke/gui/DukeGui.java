package duke.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the GUI for Duke.
 */
public class DukeGui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        createScrollContainer();
        createBottomBar();

        // Create a main layout
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        // Set anchors
        AnchorPane.setTopAnchor(this.scrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);

        AnchorPane.setLeftAnchor(this.userInput , 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        // Set the scene
        this.scene = new Scene(mainLayout);

        // Set and format the stage
        stage.setScene(this.scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinWidth(400.0);
        stage.setMinHeight(600.0);
    }

    /**
     * Creates and formats a scrollable container for the content of the chat.
     */
    private void createScrollContainer() {
        // Create and format scroll pane
        this.scrollPane = new ScrollPane();
        this.scrollPane.setPrefSize(400, 570);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        // Create and format dialog container
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(this.dialogContainer);
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogContainer.heightProperty().addListener((observable) ->
                this.scrollPane.setVvalue(1.0));
    }

    /**
     * Creates and formats a bottom bar with a input text field and a send button.
     */
    private void createBottomBar() {
        // Create and format input field
        this.userInput = new TextField();
        this.userInput.setPrefSize(335.0, 25);
        this.userInput.setOnAction((event) -> {
            updateDialogContainer();
        });

        // Create and format send button
        this.sendButton = new Button("Send");
        this.sendButton.setPrefSize(55.0, 25);
        this.sendButton.setOnMouseClicked((event) -> {
            updateDialogContainer();
        });
    }

    /**
     * Updates the dialog container with a image view and a label.
     */
    private void updateDialogContainer() {
        // Initialize labels
        String textString = this.userInput.getText();
        Label userText = new Label(textString);
        Label dukeText = new Label(this.getResponse(textString));

        // Add children to dialog container
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(this.user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(this.duke))
        );

        // Clear text field
        this.userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

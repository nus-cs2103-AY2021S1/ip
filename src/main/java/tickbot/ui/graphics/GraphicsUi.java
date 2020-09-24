package tickbot.ui.graphics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tickbot.ui.Output;
import tickbot.ui.Parser;
import tickbot.ui.Ui;

/**
 * The class to represent the graphics UI.
 */
public class GraphicsUi extends Application implements Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;

    @Override
    public void mainLoop(String[] args) {
        Application.launch(GraphicsUi.class, args);
    }

    /**
     * Performs the basic logic of the main loop.
     */
    public void performLogic() {
        OutputDialogStream outputStream = new OutputDialogStream(this);
        InputDialogStream inputStream = new InputDialogStream(this);
        Parser parser = new Parser();
        Output.setPrintStream(outputStream);
        Output.printMessage("Hello, this is Tickbot! How can I help you?");
        EventHandler<MouseEvent> handler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                String command = userInput.getText();
                Output.setPrintStream(inputStream);
                Output.printMessage(command);
                Output.setPrintStream(outputStream);
                boolean running = parser.executeCommand(command);
                if (!running) {
                    Platform.exit();
                }
                userInput.clear();
            }
        };
        sendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }

    /**
     * Creates a output dialog in the main UI.
     */
    public void writeOutputDialog(String text) {
        Dialog dialog = new OutputDialog(text);
        dialogContainer.getChildren().add(dialog);
    }
    /**
     * Creates a input dialog in the main UI.
     */
    public void writeInputDialog(String text) {
        Dialog dialog = new InputDialog(text);
        dialogContainer.getChildren().add(dialog);
    }

    @Override
    public void start(Stage stage) {
        // stage styles
        stage.setTitle("Tickbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        // elements
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        designStyles();
        performLogic();
    }

    /**
     * Designs the styles of the elements.
     */
    private void designStyles() {
        // main layout styles
        mainLayout.setPrefSize(400.0, 600.0);
        // scroll pane styles
        scrollPane.setPrefSize(400.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        String scrollPaneStyle = "-fx-border-color: grey;"
                + "-fx-focus-color: transparent;"
                + "-fx-faint-focus-color: transparent;";
        scrollPane.setStyle(scrollPaneStyle);
        // dialog container styles
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        // send button styles
        sendButton.setPrefSize(58.0, 40.0);
        String sendButtonStyle = "-fx-background-radius: 3em;";
        sendButton.setStyle(sendButtonStyle);
        // user input styles
        userInput.setPrefSize(340.0, 40.0);
        String userInputStyle = "-fx-border-color: grey;"
              + "-fx-focus-color: transparent;"
              + "-fx-faint-focus-color: transparent;";
        userInput.setStyle(userInputStyle);
        // anchor pane styles
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}

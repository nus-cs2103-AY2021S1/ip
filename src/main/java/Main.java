import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import duke.command.ExitCommand;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * <code>Main</code> starts the entire application by creating a <code>Duke</code> object and checking for existing
 * tasks.
 */
public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Stage stage;
    private final Image userImg = new Image(this.getClass().getResourceAsStream("./images/user.png"));
    private final Image dukeImg = new Image(this.getClass().getResourceAsStream("./images/bot1.jpg"));

    private Duke duke;

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText() + "\n");
        Label dukeText = new Label(getResponse(userInput.getText() + "\n"));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImg)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
        userInput.clear();
    }

    /**
     * Generates a response from <code>Duke</code> from user's input.
     *
     * @param input command from user
     * @return string representation of response from <code>Duke</code>
     */
    private String getResponse(String input) {
        try {
            input = input.trim();
            Command c = Parser.parse(input);
            String s = c.execute(input, duke.storage, Duke.UI, duke.taskList);

            if (c instanceof ExitCommand) {

                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> stage.close());
                delay.play();

            }

            return s;
        } catch (DukeException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has
     * returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              The primary stage will be embedded in the browser if the application was launched as an applet.
     *              Applications may create other stages, if needed, but they will not be primary stages and will
     *              not be embedded in the browser.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(20);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send!");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        scrollPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("FDE9F5"), CornerRadii.EMPTY,
                Insets.EMPTY)));
        mainLayout.setBackground(new Background(new BackgroundFill(Paint.valueOf("EFE2F4"), CornerRadii.EMPTY,
                Insets.EMPTY)));
        dialogContainer.setBackground(new Background(new BackgroundFill(Paint.valueOf("#D2D3F3"), CornerRadii.EMPTY,
                Insets.EMPTY)));
        stage.setScene(scene); // Setting the stage to show our screen

        stage.show(); // Render the stage.

        stage.setTitle("Yuki *Woof*");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

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

        try {
            duke = Duke.createDuke("data/duke.txt");
            if (duke.storage.isNew()) {
                dialogContainer.getChildren()
                        .add(DialogBox.getDukeDialog(getDialogLabel(Duke.UI.fileCreationSuccess()),
                                new ImageView(dukeImg)));
            } else {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(Duke.UI.welcome()),
                        new ImageView(dukeImg)));
            }
        } catch (DukeException ex) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(getDialogLabel("ERROR: " + ex.getMessage()), new ImageView(dukeImg))
            );
        }

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }
}

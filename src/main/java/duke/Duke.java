package duke;

import duke.command.Command;
import duke.command.RemindCommand;
import duke.exception.DukeException;
import duke.gui.DialogBox;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));
    private Image icon = new Image(this.getClass().getResourceAsStream("/images/send.png"));

    /**
     * Represents a Duke object that servers as a tracking bot
     * @param filePath the path for the file which will be loaded into the app
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        try {
            this.storage = new Storage(filePath);
            storage.loadData(tasks);
        } catch (DukeException e) {
            ui.showErrorLoad(e.getMessage());
            tasks.empty();
        }
    }

    public Duke () {

    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setId("UserInput");
        userInput.getStylesheets().add("/css/styles.css");
        userInput.setPromptText("Enter your command here");


        sendButton = new Button();
        ImageView icon = new ImageView(this.icon);
        icon.setFitHeight(24);
        icon.setFitWidth(30);
        sendButton.setGraphic(icon);
        sendButton.setId("Button");
        sendButton.getStylesheets().add("/css/styles.css");
        
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.getIcons().add(this.duke);
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(700.0);

        mainLayout.setPrefSize(800.0, 700.0);

        scrollPane.setPrefSize(685, 620);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(600.0);
        userInput.setPrefHeight(37);
        sendButton.setPrefWidth(82.0);
        sendButton.setPrefHeight(36);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(initialize()),
                        new ImageView(duke))
        );

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private String initialize() {
        this.ui = new Ui();
        this.tasks = new TaskList();

        try {
            this.storage = new Storage("data/tasks.txt");
            storage.loadData(tasks);
            return ui.showWelcome() + "\n" + new RemindCommand("72")
                    .execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            tasks.empty();
            return ui.showWelcome() + "\n" + ui.showErrorLoad(e.getMessage());
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (dukeText.getText().equals(ui.showBye())) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);

        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

}

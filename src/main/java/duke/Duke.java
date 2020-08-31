package duke;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jfif"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

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

    /**
     * Executes the programme provided that Duke has been initialized.
     * Following the convention for user input is crucial for an expected behavior.
     * Exception will be thrown upon undefined user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(700.0);

        mainLayout.setPrefSize(800.0, 600.0);

        scrollPane.setPrefSize(685, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(625.0);

        sendButton.setPrefWidth(55.0);

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

    private String initialize() {
        this.ui = new Ui();
        this.tasks = new TaskList();

        try {
            this.storage = new Storage("data/tasks.txt");
            storage.loadData(tasks);
            return ui.showWelcome();
        } catch (DukeException e) {
            tasks.empty();
            return ui.showWelcome() + ui.showErrorLoad(e.getMessage());
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
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event -> stage.close() );
            delay.play();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);

        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

package seedu.duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import seedu.duke.command.Command;

/**
 * Represents a chatbot that takes in and executes commands from the user.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filepath directory and name of the file to save the user's tasks to
     */
    Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.readTasks();
            this.ui = new Ui();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public Duke() {
        this("/data/taskList.txt");
    }

    /**
     * Starts the chatbot so that it takes in commands from the user.
     */
    public void run() {
        this.ui.showMessage(Message.getWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(this.ui.readCommand());
                if (command != null) {
                    Message message = command.execute(this.taskList, this.storage);
                    this.ui.showMessage(message);
                    isExit = command.isDone();
                }
            } catch (DukeException e) {
                this.ui.showMessage(new Message(e.getMessage()));
            }
        }
    }

    @Override
    public void start(Stage stage) {

        // Chat content
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        Label welcomeLabel = new Label(Message.getWelcome().getText());
        welcomeLabel.setFont(new Font("Courier New", 12));
        dialogContainer.getChildren().add(welcomeLabel);
        scrollPane.setContent(dialogContainer);

        // For user to type
        TextField userInput = new TextField();

        // Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(userInput);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);

        // Styling

        stage.setTitle("Duke");
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.show();

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().addAll(
                    getInputLabel(userInput.getText()),
                    getResponseLabel(userInput.getText()));
            userInput.clear();
        });
    }

    private Label getInputLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private Label getResponseLabel(String text) {
        String response = "";
        try {
            Command command = Parser.parse(text);
            if (command != null) {
                Message message = command.execute(this.taskList, this.storage);
                if (command.isDone()) {
                    Platform.exit();
                }
                response = message.getText();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }

        Label textToAdd = new Label(response);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

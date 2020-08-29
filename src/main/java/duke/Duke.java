package duke;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

import commands.Command;
import exceptions.DukeException;
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
import tasks.TaskList;

/**
 * Over-arching class containing the main information of the Duke bot.
 */

public class Duke extends Application {
    private TaskList tasks;
    private boolean quit;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream(
            "/images/pink.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(
            "/images/angry.png"));

    /**
     * Attempts to read an existing stored data.txt file, and creates a new data.txt file if one is not found
     */
    public Duke() {
        this.tasks = new TaskList();
        this.quit = false;
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(Paths.get("data.txt").toFile(), tasks);
            storage.readData();
        } catch (FileNotFoundException e) {
            System.out.println("No data found, creating new .txt file");
            this.storage = new Storage();
        }
    }

    /**
     * Allows the system to begin taking in user input and edits the stored data accordingly. Runs until an "exit"
     * command is received.
     */

    public void run() {
        ui.showWelcome();
        while (!quit) {
            String input = ui.takeInput();
            try {
                Command command = parser.parse(input);
                command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printException(e);
            }
            quit = parser.isQuit();
        }
    }

    @Override
    public void start(Stage stage) {
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
        stage.setResizable(true);
        stage.setMinWidth(600.0);
        stage.setMinHeight(600.0);

        mainLayout.setPrefSize(600.0, 600.0);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(525.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // what happens when the user clicks on the button
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // what happens when the user enters something in the text box
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(userImage)),
                new DialogBox(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Driver method.
     * @param args args.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

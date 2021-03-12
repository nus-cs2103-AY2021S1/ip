import java.util.Scanner;
import java.io.IOException;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an interactive chat bot named "Duke"
 * who can manage simple tasks for users.
 */
public class Duke extends Application {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image k = new Image(this.getClass().getResourceAsStream("/images/k.png"));
    private Image marco = new Image(this.getClass().getResourceAsStream("/images/marco.png"));

    private static final String LINE_TOP =    "╭⋟──────────❀• *₊。❀°。─╮\n";
    private static final String LINE_BOTTOM = "╰─────────────────────⋞╯\n";

    public Duke() {
    }

    /**
     * Constructor for Duke chat bot.
     *
     * @param filePath the relative path of assigned
     *                 file for reading and writing of data.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        File file = new File(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            System.out.println(Ui.showLoadingError());
        }
    }

    /**
     * This method simulate the interaction between
     * Duke and human users.
     *
     * @return Nothing.
     */
    public void run() {
        Ui.greet();
        while (Ui.getIn().hasNextLine()) {
            String command = ui.getUserCommand();
            Parser.respond(command, tasks, storage.filePath);
        }
    }

    /**
     * This is the main method which makes use of run method.
     * A new Duke chat bot will be initiated by reading from and writing
     * into the file of relative path "data/tasks.txt".
     *
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(25.0);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("♡ Marco ♡");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Display welcome messages when the user opens the chat bot.
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Ui().greet(), marco));

        //Handle user input sent by clicking.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        //Handle user input sent by return on keyboard.
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
        Duke testBot = new Duke("data/tasks.txt");
        String formattedInput = LINE_TOP + "                     "
                              + userInput.getText() + '\n' + LINE_BOTTOM;
        Label userText = new Label(formattedInput);
        Label dukeText = new Label(getResponse(userInput.getText(),testBot));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(formattedInput, k),
                DialogBox.getDukeDialog(getResponse(userInput.getText(),testBot), marco)
        );
        userInput.clear();
    }

    private String getResponse(String input, Duke testBot) {
        return Parser.respond(input,testBot.tasks, testBot.storage.filePath);
    }
}


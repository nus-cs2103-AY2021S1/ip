package duke;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class for the Duke program
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    public Duke(String pathname) {
        this.ui = new Ui();
        this.storage = new Storage(pathname);
        this.taskList = new TaskList(new ArrayList<>());
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.taskList = new TaskList(new ArrayList<>());
        this.parser = new Parser(new Scanner(""));
    }

    /**
     * Start the chat bot
     */
    public void run() {
        Parser parser = new Parser(new Scanner(System.in));

        try {
            this.storage.load(taskList);
        } catch (FileNotFoundException e) {
            this.ui.printReply("OOPS!!! Can't access task data.");
        } catch (IOException e) {
            this.ui.printReply("OOPS!!! Something went wrong... Tasks not saved.");
        }

        this.ui.greet();

        while (true) {
            String reply = parser.executeCommand(this.taskList);

            if (reply.equals("bye")) {
                break;
            }

            this.ui.printReply(reply);

            try {
                this.storage.save(this.taskList);
            } catch (IOException e) {
                this.ui.printReply("OOPS!!! Something went wrong... Tasks not saved.");
            }
        }

        this.ui.bye();
    }

    @Override
    public void start(Stage stage) {
        try {
            this.storage.load(taskList);
        } catch (FileNotFoundException e) {
            printReply("OOPS!!! Can't access task data.");
        } catch (IOException e) {
            printReply("OOPS!!! Something went wrong... Tasks not saved.");
        }


        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

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
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        this.parser.setScanner(new Scanner(userInput.getText()));
        String reply = this.parser.executeCommand(this.taskList);
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(reply);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            printReply("OOPS!!! Something went wrong... Tasks not saved.");
        }
    }

    private void printReply(String text) {
        Label dukeText = new Label(text);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
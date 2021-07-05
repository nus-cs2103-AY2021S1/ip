package duke;

import data.TaskList;

import javafx.application.Application;

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

import parser.Parser;

import storage.Storage;

import ui.DialogBox;
import ui.Ui;

/**
 * Encapsulates Duke, a chatbot that manages and stores a task list.
 */
public class Duke extends Application {
    private String filePath;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Sponge.jpg"));

    public Duke() {
        this.filePath = ".\\data.txt";
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser(tasks, storage);
    }

    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser(tasks, storage);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        System.out.println(ui.displayWelcome());
        boolean isExit = false;
        while (!isExit) {
            System.out.println(ui.displayLine());
            String fullInput = ui.readCommand();
            System.out.println(parser.parse(fullInput));
            isExit = parser.isExit();
        }
        ui.displayBye();

    }

    /**
     * Handles input from user, and prints output into the GUI
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        scrollPane.setContent(dialogContainer);


        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Bob");
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
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    public String getResponse(String input) {

        return parser.parse(input);
    }

    private Label getDialogLabel(String text) {

        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }


}

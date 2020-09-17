package ui;

import commands.Command;
import exceptions.DukeException;
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
import parser.CommandParser;
import parser.TaskParser;
import service.DukeResponse;
import service.DukeService;
import storage.Storage;
import javafx.application.Platform;

public class MyStage {
    private static final String SEPARATOR = "________________";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private DukeService service;
    private CommandParser commandParser;
    private TaskParser taskParser;
    private Storage storage;

    private Image user;
    private Image duke;

    private String printMessage(String message) {
        StringBuilder sb = new StringBuilder();
        return sb.append(SEPARATOR).append("\n").append(message).append(SEPARATOR).toString();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String userCommand) {
        try {
            Command newCommand = commandParser.parse(userCommand);
            newCommand.parse();
            DukeResponse response = newCommand.execute(service);
            return printMessage(response.toString());
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * Constructs a new MyStage
     * @param service a DukeService which executes commands
     * @param commandParser a CommandParser to parse commands
     * @param taskParser a TaskParser to parse tasks
     * @param storage a Storage to store and retrieve data from text files.
     */
    public MyStage(DukeService service, CommandParser commandParser, TaskParser taskParser, Storage storage) {
        this.service = service;
        this.commandParser = commandParser;
        this.taskParser = taskParser;
        this.storage = storage;
    }

    public void setImage(Image user, Image duke) {
        this.user = user;
        this.duke = duke;
    }


    private void printGreeting() {
        String text = "Hello!";
        Label dukeText = new Label(text);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userCommand = userInput.getText();

        Label userText = new Label(userCommand);
        Label dukeText = new Label(getResponse(userCommand));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (userCommand.equals("bye")) {
            try {
                storage.writeToFile(service);
            } catch (DukeException ignored) {
                printMessage(ignored.getMessage());
            }
            Platform.exit();
            System.exit(0);
        }

    }

    /**
     * Starts the show!
     * @param stage the stage of the show
     */
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
        // more code to be added here later
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.
        printGreeting();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setOnCloseRequest(e -> {
            try {
                storage.writeToFile(service);
            } catch (DukeException ignored) {
                printMessage(ignored.getMessage());
            }
            stage.close();
            Platform.exit();
            System.exit(0);
        });
    }
}

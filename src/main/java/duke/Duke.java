package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

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


import java.io.IOException;
import java.util.ArrayList;

public class Duke extends Application {
    private final String FILEPATH = System.getProperty("user.dir")
                                        + "/duke.txt";
    private String input;
    private String output;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;
    private ArrayList<String> tags = new ArrayList<>();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

//    /**
//     * Calls method run().
//     * @param args expecting the array of objects.
//     */
//    public static void main(String[] args) throws IOException {
//        new Duke();
//    }

    /**
     * Creates a Duke.
     * Initializes the Duke bot.
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        parser = new Parser();
        tasks = new TaskList();
        storage.load();
    }

    /**
     * Runs the entire program.
     * Main driver of Duke bot.
     */
    public void run(String fullCommand) throws IOException {
        input = fullCommand;
        String first = parser.parse(fullCommand);
        if (first.equals("bye")) {
            output = "Bye. Hope to see you again soon!";
            storage.appendToFile(output);
        } else {
            output = tasks.operate(storage, fullCommand, first);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        final double minHeight = 600.0;
        final double minWidth = 400.0;
        final double anchorValue = 1.0;

        //Step 1. Formatting the window to look as expected.
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, anchorValue);

        AnchorPane.setBottomAnchor(sendButton, anchorValue);
        AnchorPane.setRightAnchor(sendButton, anchorValue);

        AnchorPane.setLeftAnchor(userInput , anchorValue);
        AnchorPane.setBottomAnchor(userInput, anchorValue);

        welcomeMessage();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        String command = userInput.getText();
        System.out.println(command);
        assert !command.equals("") : "command should not be empty";

        run(command);
        String userText = this.input;
        String dukeText = getResponse(this.output);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Creates one dialog box to welcome the user & appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void welcomeMessage() throws IOException {
        output = "Hello! I'm Duke\n" + "What can I do for you?";
        storage.appendToFile(this.output);
        String dukeText = "Welcome!\n" + this.output;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: \n" + input;
    }
}
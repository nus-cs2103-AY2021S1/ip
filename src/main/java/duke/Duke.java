package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Duke class.
 * Driver class for Duke with GUI.
 * Contains task list, storage, parser and ui.
 *
 * @author YanCheng
 */

public class Duke extends Application {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser();
    public static Ui ui = new Ui(taskList, storage, parser);

    private final String HELP_TEXT = "Duke Bot Commands:\n" +
            "list : list out all current tasks\n" +
            "find <keyword> : find all task that corresponds to the keyword\n" +
            "done <task number> : marks the specified task as done\n" +
            "delete <task number> : deletes the specified task\n" +
            "todo <task name> : adds a ToDo task\n" +
            "deadline <task name> /by YYYY-MM-DD : adds a Deadline task\n" +
            "event <task name> /at YYYY-MM-DD TT:TT-TT:TT : adds an Event task\n" +
            "Do note that Date and Time must have the specified format\n";
    private Stage window;
    // output
    private TextArea outputTextArea = new TextArea("Hello! I'm Duke. \nWhat can I do for you?");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Starts Duke GUI
     * @param stage Window that is to be launched
     * @throws Exception If any exception occurs during the process
     */
    @Override
    public void start(Stage stage) throws Exception {
        // stage is window
        window = stage;
        window.setTitle("Duke Chat-Bot");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        outputTextArea.setEditable(false); // make output text area not editable
        GridPane.setConstraints(outputTextArea, 0, 0, 10, 10);

        // read duke.txt file and initialise taskList
        try {
            storage.init();
        } catch (DukeException e) {
            outputTextArea.setText(e.getMessage());
        }

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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

//        // command label
//        Label commandLabel = new Label("Command: ");
//        GridPane.setConstraints(commandLabel, 0, 10);
//
//        // command input
//        TextField commandInput = new TextField();
//        commandInput.setPromptText("Enter command");
//        GridPane.setConstraints(commandInput, 1, 10);
//
//        // enter button
//        Button enterButton = new Button("Enter");
//        GridPane.setConstraints(enterButton, 3, 10);
//        enterButton.setOnAction(e -> outputTextArea.setText(ui.readCommand(commandInput.getText())));
//
//        // help button
//        Button helpButton = new Button("Help");
//        GridPane.setConstraints(helpButton, 1, 11);
//        helpButton.setOnAction(e -> PopUpBox.display("Help menu", HELP_TEXT));
//
//        grid.getChildren().addAll(outputTextArea, commandLabel, commandInput, helpButton, enterButton);
//
//        Scene defaultScene = new Scene(grid, 600, 400);
//        window.setScene(defaultScene);
//        window.show();
    }

    /**
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return ui.readCommand(input);
    }

}

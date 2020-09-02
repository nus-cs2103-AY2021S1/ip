package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;


public class Duke extends Application {

    /** Storage object of the Duke object */
    private Storage storage;

    /** TaskList object of the Duke object */
    private TaskList tasks;

    /** Ui object of the Duke object */
    private Ui ui;

    /** Directory of file to store tasks */
    protected static String MEMORY_FILE_DIR = "./data/";

    /** Name of the file to store tasks */
    protected static String MEMORY_FILE_NAME = "task_list.txt";

    /** Image objects of user and chatbot initialized */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/standard_user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/standard_robot.png"));

    /** Components of the  */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Constructor of Duke.
     * Initialize storage, tasks, ui of Duke object.
     */
    public Duke() {
        this.storage = new Storage(MEMORY_FILE_DIR, MEMORY_FILE_NAME);
        try {
            this.tasks = new TaskList(storage.readMemoTasks(), MEMORY_FILE_DIR, MEMORY_FILE_NAME);
        } catch (Exception ex) {
            tasks = new TaskList();
            List<String> output = HandleException.handleException(DukeException.ExceptionType.READ_FILE);
            for (String s : output) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(s, duke));
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("Let us start with an empty list first!", duke));
        }

        this.ui = new Ui(MEMORY_FILE_DIR, MEMORY_FILE_NAME, tasks.showList());
    }


    public static void main(String[] args) {

    }


    /**
     * Set up the Interface and functions
     *
     * @param stage  Stage object for the set up
     */
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
        stage.setMinWidth(400.0);

        //mainLayout.setPrefSize(385, 535);
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String[] dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user));

        for (String response : dukeText) {
            dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(response, duke));
        }

        userInput.clear();
    }


    /**
     * Returns an array of output text messages
     */
    String[] getResponse(String input) {
        return ui.processRequests(input);
    }

}


//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh
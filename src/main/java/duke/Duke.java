package duke;

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


/**
 * Presents Duke class with "Ka To"
 * Interacts with the user and answers user commands
 * Starts the GUI chat window
 * Manages the task list of the user
 * Follows the coding standard
 */

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;


    private Image user = new Image(this.getClass().getResourceAsStream("/images/Me.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/KaTo.png"));
    private Image userBackground = new Image(this.getClass().getResourceAsStream("/images/background2.png"));
    private Image dukeBackground = new Image(this.getClass().getResourceAsStream("/images/Sea.png"));

    public Duke(){
    }
    public Duke(String filePath) {
        //initialise ui and storage
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        //load the storage file into task list
        if (this.storage.load().isEmpty()) {

            // create new task list if empty file
            this.tasks = new TaskList();
        }else{
            this.tasks = new TaskList(storage.load());
        }
    }

    //run the ui and parser
    private void run() {
        // KaTo greets the user
        this.ui.printGreet();

        // KaTo takes in user commands
        while (this.ui.input.hasNextLine()) {
            String command = this.ui.getInput();
            Parser.processCommand(command, this.ui, this.tasks, this.storage.filePath);
        }
    }

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // KaTo greats user in the chat window
        Label dukeText = new Label("Hello, Ka To here, how can I serve you?");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, duke, dukeBackground)
        );

        Scene scene = new Scene(mainLayout);
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private void handleUserInput() {

        Label userText = new Label("Me :\n" + userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user, userBackground),
                DialogBox.getDukeDialog(dukeText, duke, dukeBackground)
        );
        userInput.clear();
    }

    /**
     * Gets and processes user commands
     * Returns the response to those commands
     *
     * @param input String user input
     * @return output String KaTo response
     */
    private String getResponse(String input) {

        String filePath = "./data/duke.txt";
        GUI gui = new GUI();
        this.storage = new Storage(filePath);

        if (this.storage.load().isEmpty()) {
            this.tasks = new TaskList();
        }else{
            this.tasks = new TaskList(storage.load());
        }
        // get the KaTo response
        String output = ParserGUI.processCommand(input, gui, this.tasks, this.storage.filePath);

        return "Ka To: \n" + output;
    }



    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke manager = new Duke(filePath);
        manager.run();
    }

}

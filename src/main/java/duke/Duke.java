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
        //The better Gui includes a chat background and a circular profile page
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();

        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        Scene scene = new Scene(mainLayout);

        setMainLayout(mainLayout, sendButton);

        setDialogContainer();

        setStage(stage, scene);

        setScrollPane();

        setUserInput();

        setSendButton(sendButton);

        setAnchorPane(sendButton);

    }

    private void setMainLayout(AnchorPane mainLayout, Button sendButton){
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
    }

    private void setUserInput(){
        userInput.setPrefWidth(345.0);

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void setDialogContainer(){

        // KaTo greats user in the chat window
        Label dukeText = new Label("Hello, Ka To here, how can I serve you?");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, duke, dukeBackground)
        );

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private void setStage(Stage stage, Scene scene){
        stage.setScene(scene);
        stage.show();

        stage.setTitle("KaTo Task Manager");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setScrollPane(){

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 575);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setAnchorPane(Button button){
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(button, 1.0);
        AnchorPane.setRightAnchor(button, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }

    private void setSendButton(Button sendButton){

        sendButton.setPrefWidth(55.0);

        sendButton.setOnMouseClicked((event) -> {
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

package duke;

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

/**
 * The Duke program can record down todos, deadlines and events and save it on your computer.
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/DaLogo.png"));

    /**
     * Duke constructor to initialize a Duke object, initializes a Ui, Storage and TaskList object.
     * @exception DukeException On input error and file path error.
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.loadFile());

    }

    @Override
    public void start(Stage stage) throws DukeException {

        // The container for the content of the chat to scroll.
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

        // Formatting the window to look as expected
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
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(new DialogBox(new ImageView(logo)));

        // adding functionality to handle user input.
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
        try {
            assert !input.isBlank();
            String response = parseUserInput(input);
            assert !response.isBlank();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    /**
     * Main method which runs the bot
     * @param args user input
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }

    /**
     * parseUserInput method which handles the inputs and responds to the user while calling the appropriate classes
     * @param input user input
     * @exception DukeException On input error and file path error.
     */
    public String parseUserInput(String input) throws DukeException {
        String output = "";
        // splits the input into the different words in order to understand what the user wants
        String[] inputArray = input.split(" ", 2);
        String userCommand = inputArray[0];
        switch (userCommand) {
        case "bye":
            output += ui.bye();
            return output;
        case "list":
            output += ui.printList(taskList.getTasks());
            output += ui.drawLine();
            return output;
        case "done":
            output += ui.doneTask(taskList.done(Integer.parseInt(inputArray[1])));
            output += ui.listCount(taskList.countList());
            output += ui.drawLine();
            storage.saveFile(taskList.getTasks());
            return output;
        case "todo":
            output += ui.addTask(taskList.addTodo(inputArray[1]));
            output += ui.listCount(taskList.countList());
            output += ui.drawLine();
            storage.saveFile(taskList.getTasks());
            return output;
        case "deadline":
            output += ui.addTask(taskList.addDeadline(inputArray[1]));
            output += ui.listCount(taskList.countList());
            output += ui.drawLine();
            storage.saveFile(taskList.getTasks());
            return output;
        case "event":
            output += ui.addTask(taskList.addEvent(inputArray[1]));
            output += ui.listCount(taskList.countList());
            output += ui.drawLine();
            storage.saveFile(taskList.getTasks());
            return output;
        case "delete":
            output += ui.deleteTask(taskList.delete(inputArray[1]));
            output += ui.listCount(taskList.countList());
            output += ui.drawLine();
            storage.saveFile(taskList.getTasks());
            return output;
        case "find":
            output += ui.foundWord(taskList.findWord(inputArray[1]));
            return output;
        default:
            return "Sorry I don't know what you mean. \nType /help to see the list of commands available!";
        }
    }
}

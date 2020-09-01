import java.io.File;
import java.util.Scanner;

import Command.Command;
import DukeComponent.Parser;
import DukeComponent.Ui;
import TaskList.Storage;
import TaskList.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Duke asks user for their todos and makes a todo list.
 * Tasks can be viewed in a list, marked as done and deleted.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
//    private TaskList tasks;
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The default constructor for a Duke object.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage);
    }


    /**
     * This constructor takes in the file to save data.
     */
    public Duke(File file) {
        storage = new Storage(file);
        tasks = new TaskList(storage);
    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        String dukeText = getResponse(userInput.getText());
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, user),
//                DialogBox.getDukeDialog(dukeText, duke)
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
//        return "Duke heard: " + input;
        Command command = Parser.parse(input, tasks.size());
        return command.act(tasks);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//    }

//    /**
//     * This method collects commands from user until an exit command 'bye' is read.
//     * Valid commands will be executed.
//     */
//    void run() {
//        Scanner scanner = new Scanner(System.in);
//        String s;
//        Ui.welcomeMessage();
//        Command command;
//        while ((command = Parser.parse(scanner.nextLine(), tasks.size())).getType()
//                != Command.CommandType.EXIT) {
//            command.act(this.tasks);
//        }
//        command.act(this.tasks);
//    }
//
//    /**
//     * This method is the main method.
//     */
//    public static void main(String[] args) {
//        Duke duke = new Duke();
//        duke.run();
//    }
}

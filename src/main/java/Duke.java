import java.io.IOException;
import java.util.Scanner;

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
import gui.DialogBox;

/**
 * Represents the Duke Chatbot.
 * Allows users to store a list of Tasks, consisting of three types,
 * namely Todos, Events and Deadlines. Users input, change and delete
 * such items via text commands to the bot.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image man = new Image(this.getClass().getResourceAsStream("/images/Man.jpg"));
    private Image woman = new Image(this.getClass().getResourceAsStream("/images/Woman.jpg"));
    /**
     * Builds an instance of the Duke Chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot instance.
     * Chatbot will begin listening for commands with this method.
     */
    public void run() {
        try {
            ui.intro();
            Scanner input = new Scanner(System.in);

            // Uses user input text and acts accordingly.
            loop:
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Command command = ui.parseCommand(line);
                ui.lineBreak();

                switch (command.name) {
                case "find":
                    tasks.findTasks(command.message);
                    break;
                case "bye":
                    System.out.println("That's it? That's a shame. Well, see you later then.");
                    ui.lineBreak();
                    storage.saveTasks();
                    break loop;
                case "list":
                    tasks.printList();
                    break;
                case "done":
                    tasks.taskDone(command.index);
                    break;
                case "delete":
                    tasks.removeFromList(command.index);
                    break;
                case "error":
                    System.out.println(command.message);
                    break;
                default:
                    tasks.addToList(command.task);
                }
                ui.lineBreak();
            }

            input.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

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

        //Step 2. Formatting the window to look as expected
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

        //Step 3. Add functionality to handle user input.
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(man)),
                DialogBox.getDukeDialog(dukeText, new ImageView(woman))
        );
        userInput.clear();
    }

}

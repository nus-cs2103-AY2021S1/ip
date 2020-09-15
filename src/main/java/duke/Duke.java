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
 * Represents a Duke chat bot. A <code>Duke</code> object
 * has a specific storage instance, a list of tasks, and
 * a user interface.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Duke(String filePath) {
        TaskList taskList;
        this.storage = new Storage(filePath);
        try {
            taskList = this.storage.loadList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("Creating a new list instead...");
            // perhaps create the specified filePath and request for user confirmation?
            taskList = new TaskList();
        }
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public Duke() {
        TaskList taskList;
        this.storage = new Storage("./data/duke.txt");
        try {
            taskList = this.storage.loadList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("Creating a new list instead...");
            // perhaps create the specified filePath and request for user confirmation?
            taskList = new TaskList();
        }
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Starts the Duke ChatBot with storage file at ./data/duke.txt.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke("./data/duke.txt");
        chatBot.run();
    }

    private void run() {
        System.out.println(ui.getLogo());
        System.out.println(ui.getWelcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String query = ui.readLine();
                Command c = Parser.parse(query);
                String response = c.execute(this.taskList, this.ui, this.storage);
                System.out.println(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.getError(e));
            }
        }
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
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        String response;
        try {
            Command c = Parser.parse(input);
            assert c != null: "No command available";
            response = c.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException e) {
            response = ui.getError(e);
        }
        return response;
    }
}


package bob;

import java.io.IOException;

import bob.commands.Command;
import bob.common.Messages;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;
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


/**
 * Represents the task-managing ChatBot.
 */
public class Bob extends Application {
    private final Storage storage;
    private final Tasklist tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Creates a Bob.
     *
     * @throws IOException If saved file can't be loaded.
     */
    public Bob() throws IOException {
        String filePath = System.getProperty("user.dir").endsWith("text-ui-test")
                ? "test.txt"
                : System.getProperty("user.dir").endsWith("ip")
                ? "data/bob.txt"
                // Creates a save file on the user's home directory if user is not in ip directory
                : System.getProperty("user.home") + "/bob.txt";
        Tasklist tempTasks;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tempTasks = new Tasklist(storage);
        } catch (BobException | IOException e) {
            System.out.println(Messages.LOADING_ERROR);
            tempTasks = new Tasklist();
        }
        this.tasks = tempTasks;
    }

    /**
     * Runs Bob.
     */
    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExited();
            } catch (IOException e) {
                ui.showUpdatingError();
            } catch (BobException e) {
                ui.showError(e);
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * The main environment where Bob runs.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //File path of saved task list
        try {
            new Bob().run();
        } catch (IOException e) {
            System.out.println(Messages.INVALID_PATHNAME);
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Bob");
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
}

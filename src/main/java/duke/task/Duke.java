package duke.task;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Encapsulates the chat bot program.
 * Yoo is a chat bot program you can use to record tasks.
 * You can add three types of tasks: todo, deadline and event.
 * You can add and delete tasks using commands.
 * Terminate the program using the bye command.
 *
 * @author Jace Tan
 * @version 0.1
 * @since 2020-08-27
 */

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the chat bot program.
     * @param filePath Path to the file that stores tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui(tasks);
    }

    /**
     * Runs the chat bot program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        String isExit = "";
        while (!isExit.equals("bye")) {
            try {
                String command = sc.nextLine();
                isExit = Parser.parse(command, tasks, ui, storage);
            } catch (YooException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
        ui.showExit();
    }

    /**
     * Starts Duke in GUI.
     * @param stage is necessary component.
     */
    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Yoo");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 550.0);

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String result;
        try {
            result = Parser.parse(input, tasks, ui, storage);
        } catch (YooException e) {
            result = e.getMessage();
        }
        return result;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

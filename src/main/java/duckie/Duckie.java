package duckie;

import duckie.ui.Ui;
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


import java.nio.file.Path;
import java.nio.file.Paths;

import duckie.command.Command;
import duckie.exception.DuckieException;
import duckie.task.TaskList;

/**
 * Main file for the chatbot Duckie
 */
public class Duckie extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Instantiate the Duckie object together with the filePath of duckie file
     * @param filePath Duckie file location
     */
    public Duckie(String filePath) {
        ui = new Ui();
        ui.showIntro();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DuckieException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public Duckie() {
        String cwd = System.getProperty("user.dir");
        Path filePath = Paths.get(cwd, "data", "duckie.txt");
        Duckie duckie = new Duckie(String.valueOf(filePath));
        this.ui = duckie.ui;
        this.storage = duckie.storage;
        this.tasks = duckie.tasks;
    }

    /**
     * Activates the chatbot to take in commands
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckieException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(595, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        userInput = new TextField();
        userInput.setPrefWidth(510.0);
        sendButton = new Button("Send");
        sendButton.setPrefWidth(80.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        AnchorPane mainLayout = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(600.0, 600.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Duckie");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(600.0);
        stage.show();

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

    /**
     * Main method activating run() method
     * @param args
     */
    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        Path filePath = Paths.get(cwd, "data", "duckie.txt");
        new Duckie(String.valueOf(filePath)).run();
    }
}

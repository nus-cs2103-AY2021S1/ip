package duke.duke;

import java.io.File;
import java.util.concurrent.TimeUnit;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * Represents Duke object. Is responsible for the overall running of Duke.
 *
 * @author Kishen Ashok Kumar
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage store;
    private TaskList tasks;
    private UI ui;
    private Image userImage;
    private Image dukeImage;
    private boolean isExit;

    /**
     * Creates a Duke object and sets all the attributes
     * to null.
     */
    public Duke() {
        store = new Storage("./data", "duke.txt");
        File loadFile = store.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
        this.ui = new UI();
        userImage = new Image(
                this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(
                this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    /**
     * Creates a Duke object with the respective directory and file paths
     * for loading and saving of task list.
     *
     * @param directoryPath directory path containing text file to load.
     * @param filePath name of text file to load.
     */
    public Duke(String directoryPath, String filePath) {
        ui = new UI();
        store = new Storage(directoryPath, filePath);
        File loadFile = store.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the instance of Duke.
     * Is responsible for the coordination of all Duke functions.
     */
    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, store);
            isExit = command.isExit();
            if (!isExit) {
                ui.displayBlankLine();
            }
        }
    }

    /**
     * Creates an instance of a Duke object and runs it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data", "duke.txt");
        duke.run();
    }

    /**
     * Starts JavaFX.
     * @param stage Stage object to show the user.
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

        // more code to be added here later
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

        ImageView dukePic = new ImageView(dukeImage);
        dukePic.setClip(new Circle(50, 50, 50));

        DialogBox dukeGreeting = DialogBox
                                         .getDukeDialog(new Label(ui.displayGreeting()), dukePic);

        Insets padding = new Insets(10, 0, 10, 0);
        dukeGreeting.setPadding(padding);
        dukeGreeting.setSpacing(10);

        dialogContainer.getChildren().addAll(dukeGreeting);

        // more code to be added here later
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        ImageView dukePicture = new ImageView(dukeImage);
        ImageView userPicture = new ImageView(userImage);
        Insets padding = new Insets(10, 0, 10, 0);
        dukePicture.setClip(new Circle(50, 50,50));
        userPicture.setClip(new Circle(50,50,50));

        DialogBox userDialog = DialogBox.getUserDialog(userText, userPicture);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukePicture);
        userDialog.setPadding(padding);
        dukeDialog.setPadding(padding);
        userDialog.setSpacing(10);
        dukeDialog.setSpacing(10);


        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
        if (isExit) {
            Platform.exit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        String response = command.execute(tasks, ui, store);
        isExit = command.isExit();
        return response;
    }

}

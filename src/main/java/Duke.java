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

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

//TODO: Stretch Goals: Level 8- Use date related command

/**
 * Driver of {@code Duke} programme.
 */
public class Duke {

    //UI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //Image
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static final String DATA_FILE = "data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the Duke programme and load tasks from default file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_FILE);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Initialises the Duke programme and load tasks from data file.
     *
     * @param filePath Path of data file to be loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Executes the {@code Duke} programme.
     */
    public void run() {
        while (!Command.isTerminated) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(taskList, ui);
            } catch (DukeException de) {
                ui.printError(de);
            }
        }
    }

    /**
     * Performs saving and clean up on programme termination.
     */
    public void terminate() {
        storage.saveToFile(taskList.export());
        ui.showExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(DATA_FILE);
        duke.run();
        duke.terminate();
    }

    public void start(Stage stage) {
        if (true)
            return;

        // Setting up required components
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

        scrollPane.setPrefSize(385,535);
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
        AnchorPane.setBottomAnchor(userInput,1.0);

        //Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel((userInput.getText())));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        //Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getDukeDialog(dukeText.getText(), duke)
        );
        userInput.clear();
    }



    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

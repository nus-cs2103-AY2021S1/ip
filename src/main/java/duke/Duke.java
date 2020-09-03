package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import duke.command.CommandExecutor;
import duke.command.DukeCommandExecutor;
import duke.exception.DukeException;
import duke.exception.InvalidSaveFileException;
import duke.storage.DukeStorage;
import duke.storage.Storage;
import duke.task.TaskArrayList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

// Main class that initializes the program.
public class Duke extends Application {
    private static final String START_MSG = "Hello! I'm Duke.\nWhat can I do for you?";
    private final Scanner sc = new Scanner(System.in);
    private final CommandExecutor exe = new DukeCommandExecutor();
    private final Ui ui = new Ui();
    private final Path savePath;
    private final Storage storage;
    private final TaskArrayList taskList;

    // JavaFX related fields
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    /**
     * Initialises the Duke chatbot with a default save path.
     *
     * @throws IOException If an IO error occurs while loading the save file.
     */
    public Duke() throws IOException {
        this.savePath = Paths.get("data", "duke.txt");
        this.storage = new DukeStorage(savePath);
        this.taskList = new TaskArrayList(storage);
    }

    /**
     * Initialises the Duke chatbot with the given save path.
     *
     * @throws IOException If an IO error occurs while loading the save file.
     */
    public Duke(Path savePath) throws IOException {
        this.savePath = savePath;
        this.storage = new DukeStorage(savePath);
        this.taskList = new TaskArrayList(storage);
    }

    /**
     * Starts the program on the cli.
     */
    public void run() throws InvalidSaveFileException {
        try {
            loadSave();
        } catch (DukeException e) {
            e.printStackTrace();
            throw new InvalidSaveFileException("An error has occurred while loading the save file!");
        }

        ui.print(START_MSG);
        while (true) {
            String input = sc.nextLine().trim();
            try {
                String msgBody = exe.execute(input, taskList);
                ui.print(msgBody);
                if (exe.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.printErr(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) throws InvalidSaveFileException {
        try {
            loadSave();
        } catch (DukeException e) {
            e.printStackTrace();
            throw new InvalidSaveFileException("An error has occurred while loading the save file!");
        }

        createNodes(stage);
        initialiseNodesStyles(stage);
        initialiseNodesEvents(stage);
        generateStartMsgDialog();
    }

    private void createNodes(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void initialiseNodesStyles(Stage stage) {
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
        scrollPane.setStyle("-fx-background: #212121;");

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setSpacing(20);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void initialiseNodesEvents(Stage stage) {
        sendButton.setOnMouseClicked((event) -> handleUserInput(stage));

        userInput.setOnAction((event) -> handleUserInput(stage));
    }

    private void generateStartMsgDialog() {
        Label startMsg = new Label(getResponse(START_MSG));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(startMsg, new ImageView(duke)));
    }

    private void handleUserInput(Stage stage) {
        String input = userInput.getText();
        if (input.equals("")) {
            return;
        }
        String response;
        try {
            response = exe.execute(input, taskList);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(response));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

        if (exe.shouldExit()) {
            userInput.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

    private String getResponse(String input) {
        return "Duke:\n" + input;
    }

    private void loadSave() throws DukeException {
        List<String> loadedLines = storage.getSavedLines();
        for (String line: loadedLines) {
            exe.loadSaveString(line, taskList);
        }
    }

    public static void main(String[] args) throws IOException, InvalidSaveFileException {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}

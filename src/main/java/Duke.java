import duke.Command;
import duke.Storage;
import duke.Ui;
import duke.resource.Parser;
import duke.resource.TaskList;
import duke.util.DukeException;
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
 * Duke is a bot that functions as a user's task manager.
 */

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Overloaded constructor that creates a Duke object, so Launcher can be ran.
     */

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./src/main/data/duke.txt");
    }

    /**
     * Overloaded constructor that creates a Duke object with the path the text
     * file will be saved in.
     *
     * @param filePath the filepath task sessions will be saved in
     */

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ui.welcome();
        try {
            tasks = TaskList.parse(this.storage.load());
            ui.printLoaded(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    /**
     * Runs Duke's user input scanning that only terminates when a "bye" command is given.
     */

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.read();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.shouldExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        userInput.setOnAction(event -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a new Duke object and runs it, starting the entire chat-bot.
     *
     * @param args input arguments for main; unused
     */

    public static void main(String[] args) {
        // change the filePath below to save elsewhere
        new Duke("./src/main/data/duke.txt").run();
    }
}

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents a Duke robot that deals with multiple tasks.
 */
public class Duke extends Application{
    /**
     * The <code>Storage</code> used in Duke.
     */
    private Storage storage;
    /**
     * The list of tasks.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The <code>Parser</code> used in Duke.
     */
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.jpg"));

    /**
     * Creates a new <code>Duke</code> with the given <code>filePath</code>.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Creates a new <code>Duke</code>.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("C:\\Users\\e0316059\\Desktop\\Duke\\src\\main\\java\\data\\Duke.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.parser = new Parser(ui, tasks, storage);
        parser.run();
    }

    /**
     * Runs Duke with the given filePath.
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\e0316059\\Desktop\\Duke\\src\\main\\java\\data\\Duke.txt").run();
    }

    /**
     * Creates and specifies the graphical user interface.
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     * @param input the user command.
     * @return a response message to user.
     */
    private String getResponse(String input) {
        this.parser = new Parser(ui, tasks, storage);
        String response = "";
        try {
            response = parser.handleCommand(input);
        } catch (DukeException e) {
            response = ui.showCommandError(e);
        }
        return response;
    }
}
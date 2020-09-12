package butler;

import butler.command.Command;
import butler.exception.ButlerException;
import butler.io.Parser;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
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
 * Represents a butler that manages a list of tasks for the user.
 *
 * Butler maintains a list of tasks across different sessions.
 * Data of the list of tasks are saved in hard disk within data/tasks.txt
 * relative to the program file location.
 */
public class Butler extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image butler = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Butler(){
    }

    private Butler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ButlerException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Starts the application GUI to display "Hello World!".
     *
     * @param stage Stage for this application, where the scene is set.
     */
    @Override
    public void start(Stage stage) {

        //Step 1.Setting up required components.
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        Butler b = new Butler("data/tasks.txt");
        String welcomeMsg = b.ui.showWelcome();
        Label welcomeText = new Label(welcomeMsg);
        dialogContainer.getChildren().addAll(
                DialogBox.getButlerDialog(welcomeText, new ImageView(butler))
        );

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Butler");
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
            handleUserInput(b);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(b);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // more code to be added here later
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Butler's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Butler b) {
        Label userText = new Label(userInput.getText());
        Label butlerText = new Label(getResponse(userInput.getText(), b));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getButlerDialog(butlerText, new ImageView(butler))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input, Butler b) {
        try {
            Command c = Parser.parse(input);
            return c.execute(b.tasks, b.ui, b.storage);
        } catch (ButlerException e) {
            return b.ui.showError(e.getMessage());
        }
    }
}

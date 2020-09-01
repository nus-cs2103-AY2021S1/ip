package duke;

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
 * A task manager.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(getClass().getResourceAsStream("/images/user.png"));
    private Image zoroark = new Image(getClass().getResourceAsStream("/images/zoroark.png"));

    @Override
    public void init() {
        try {
            storage = new Storage("./data/Duke.txt");
            taskList = new TaskList(storage.readFromFile());
            ui = new Ui();
        } catch (DukeException e) {
            ui.addMessage(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        stage.setTitle("Zoroark");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);

        mainLayout.setPrefSize(800.0, 600.0);

        scrollPane.setPrefSize(800.0, 560);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(725.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 0.0);

        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput, 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(
                DialogBox.getZoroarkDialog(new Label(ui.getWelcome()), new ImageView(zoroark)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getZoroarkDialog(dukeText, new ImageView(zoroark))
        );
        userInput.clear();
        ui.clearResponse();
    }

    /**
     * Gets Duke's response.
     *
     * @param input the input string.
     * @return Duke's response.
     */
    private String getResponse(String input) {
        try {
            Command next = Parser.parse(input);
            next.execute(taskList, storage, ui);
        } catch (DukeException e) {
            ui.clearResponse();
            ui.addMessage(e.getMessage());
        }
        return ui.getResponse();
    }
}

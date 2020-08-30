package duke;

import duke.ui.DialogBox;
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

import duke.exec.Executable;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke extends Application {

    // instance variables (constructor)
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // instance variables (start)
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    // constructor
    public Duke() {
        this("data", "./data/duke.txt");
    }
    /**
     * Constructs a Duke object that represents
     * a particular Duke session
     * @param directory the directory where the database is located
     * @param path path to the database file
     */
    public Duke(String directory, String path) {
        storage = new Storage(directory, path);
        tasks = new TaskList(storage.load());
        ui = new Ui();
    }

    /**
     * Helper method that creates two dialog boxes, one for the user
     * and the other for duke. Clears the user input after
     * processing
     */
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String output;

        // process user input
        try {
            Executable e = Parser.parse(input);
            output = e.run(tasks, ui, storage);
        } catch (DukeException e) {
            output = ui.output(e.getMessage());
        }

        // set labels and dialog container
        Label userText = new Label(input);
        Label dukeText = new Label(output);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        // initialize components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(10.0);
        dialogContainer.getChildren()
                .addAll(DialogBox.getDukeDialog(new Label(ui.greeting()), new ImageView(duke)));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // set configurations for components
        stage.setTitle("orang");
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

        stage.setScene(scene);
        stage.show();

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> handleUserInput()); // for button
        userInput.setOnAction((event) -> handleUserInput()); // for textfield
    }

}

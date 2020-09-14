package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Driver to run the Duke app.
 */
public class Duke extends Application {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Font font;

    /**
     * Creates new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.readFile());
    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        font = new Font("Consolas", 14);

        setStyle(stage);
        setActions();

        dialogContainer.getChildren().add(getDialogLabel(ui.getWelcome()));
    }

    private void setStyle(Stage stage) {

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        userInput.setFont(font);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setActions() {

        sendButton.setOnMouseClicked((event) -> {
            updateDialog();
        });

        userInput.setOnAction((event) -> {
            updateDialog();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setMinHeight((text.split("\n").length + 1) * font.getSize() * 1.4);
        textToAdd.setFont(font);

        return textToAdd;
    }

    private void updateDialog() {

        String reply = getReply(userInput.getText());

        dialogContainer.getChildren().add(getDialogLabel(reply));
        userInput.clear();
    }

    private String getReply(String input) {

        if (input.equals("bye")) {
            storage.saveFile(taskList.getList());
            Platform.exit();
            return "";
        } else {
            try {
                return input + "\n" + ui.getLine() + Parser.parse(input, taskList, ui);
            } catch (DukeException e) {
                return input + "\n" + ui.getLine() + e.getMessage() + "\n" + ui.getLine();
            }
        }
    }

    /**
     * Runs the Duke app.
     */
    public void run() {

        ui.printWelcome();

        String input = ui.readLine();

        while (!input.equals("bye")) {
            try {
                Parser.parse(input, taskList, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                ui.printLine();
            } finally {
                input = ui.readLine();
            }
        }

        storage.saveFile(taskList.getList());

        ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.printLine();
    }
}

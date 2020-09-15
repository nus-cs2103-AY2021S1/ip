package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * Duke the best chatbot hehe
 */
public class Duke extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        //tring dukeHeard = getResponse(userInput.getText());
        String dukeReply = getReply(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                //DialogBox.getDukeDialog(dukeHeard, duke),
                DialogBox.getDukeDialog(dukeReply, user)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public String getReply(String input) {
        return handleCommand(input);
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Init Duke
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.readData());
        parser = new Parser();
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Take in command
     */

    public String addNewLines(String cur, String add) {
        cur += add + "\n";
        return cur;
    }

    public String handleCommand(String cmd) {
        MyString respond = new MyString();

        Parser.CommandType commandType = parser.getType(cmd);

        switch (commandType) {
            // commands handled by TaskList
            case LIST:
                tasks.handleList(respond);
                break;
            case DELETE:
                tasks.handleDelete(cmd, respond);
                break;
            case FIND:
                tasks.handleFind(cmd, respond);
                break;
            case DONE:
                tasks.handleDone(cmd, respond);
                break;
            case TODO:
                tasks.handleToDo(cmd, respond);
                break;
            case EVENT:
                tasks.handleEvent(cmd, respond);
                break;
            case DEADLINE:
                tasks.handleDeadline(cmd, respond);
                break;
            case DOWITHIN:
                tasks.handleDoWithin(cmd, respond);
                break;
            // commands handled by UI only
            case BYE:
                ui.sayBye(respond);
                break;
            case INVALID:
                ui.invalidCommand(respond);
                break;
            default:
                break;
        }

        storage.updateDataFile(tasks.getArrayList());

        return respond.toString();
    }

    public void run() {
        ui.sayHi();
        Scanner myScanner = new Scanner(System.in);
        while(true) {
            String cmd = myScanner.nextLine();
            handleCommand(cmd);
        }
    }

    /**
     * check if date is yyyy-mm-dd, then format to MMM d yyyy
     */
}

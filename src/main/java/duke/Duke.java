package duke;

import duke.commands.Command;
import duke.commands.CommandOutput;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.io.IO;

import duke.parser.CommandLineInterfaceParser;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.ui.DialogBox;

import duke.utils.Colour;
import duke.utils.Messages;
import duke.utils.ResourceHandler;
import duke.utils.Ui;

import javafx.application.Application;
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



public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.taskManager = storage.load();
        } catch (InvalidFilePathException e) {
            ui.print(Colour.convertTextToRed(e.getMessage()));
        } catch (StorageOperationException e) {
            ui.print(Colour.convertTextToRed(e.getMessage()));
        }
    }

    public void run() {
        ui.print(ResourceHandler.getMessage("commandline.welcomeMessage"));
        IO io = new IO();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = io.readUserInput();
                CommandOutput output = getCommandExecutionOutput(userInput);
                ui.print(output.getCommandOutput());
                isExit = output.isExit();
            } catch (IllegalArgumentException e) {
                ui.print(Colour.convertTextToRed(Messages.INVALID_COMMAND_INPUT_MESSAGE));
            }
        }
        System.exit(0);
    }

    public CommandOutput getCommandExecutionOutput(String userCommand) {
        try {
            Command parsedUserCommand = CommandLineInterfaceParser.parse(userCommand);
            CommandOutput commandOutput = parsedUserCommand.executeCommand(taskManager, storage);
            return commandOutput;
        } catch (DukeException e) {
            return new CommandOutput(e.getMessage(), false);
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getUserDialog(dukeText, duke)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}




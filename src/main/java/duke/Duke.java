package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import duke.command.Command;
import duke.control.DialogueBox;
import duke.core.*;
import duke.handle.CommandNotFoundException;
import duke.handle.TaskNotFoundException;
import duke.handle.LoadingException;
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
 * The Duke class represents a duke bot that can interact with
 * the user and help the user to manage their tasks, which can
 * help to store the tasks entered by the user into a local record, add
 * the tasks, remove the tasks, read the tasks in the record and
 * present them to the user, and help to
 * search for a specific task for the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "data/duke.txt";
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public TextField userInput;
    public VBox dialogContainer;
    public ScrollPane scrollPane;
    public AnchorPane mainlayout;
    public Button sendButton;

    /*
    public void run() {
        //System.out.println("\u2718");
        this.ui.showGreeting();
        boolean isContinuing = true;
        while (isContinuing) {
            try {
                String command = this.ui.readCommand();
                Command parsedCommand = Parser.parseCommand(command);
                parsedCommand.excecute(taskList, ui, storage);
                isContinuing = parsedCommand.isContinuing();
            } catch (CommandNotFoundException commandNotFoundexException) {
                //System.out.println(commandNotFoundexException.getMessage());
                ui.handle(commandNotFoundexException);
            } catch (TaskNotFoundException taskNotFoundException) {
                ui.handle(taskNotFoundException);
            } catch (IOException ioException) {
                ui.handle(ioException);
            }
        }
    }
    */

    /*
    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }
    */
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Label label = new Label("Hello");
        //Scene scene = new Scene(label);
        //primaryStage.setScene(scene);
        //primaryStage.show();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable -> {
            scrollPane.setVvalue(1.0);
        }));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        Scene scene = new Scene(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        primaryStage.setScene(scene);
        primaryStage.show();

    }
    */

    /*
    public Label getLabel(String string) {
        Label label = new Label(string);
        label.setWrapText(true);
        return label;
    }
    */

    public Result getResponse(String command) {
        try {
            Command parsedCommand = Parser.parseCommand(command);
            return parsedCommand.excecute(taskList, ui, storage);
            //isContinuing = parsedCommand.isContinuing();
        } catch (CommandNotFoundException commandNotFoundexException) {
            //System.out.println(commandNotFoundexException.getMessage());
            return new Result(ui.handle(commandNotFoundexException), true);
        } catch (TaskNotFoundException taskNotFoundException) {
            return new Result(ui.handle(taskNotFoundException), true);
        } catch (IOException ioException) {
            return new Result(ui.handle(ioException), true);
        }

        //Command parsedCommand = Parser.parseCommand(command);
        //return parsedCommand.excecute(taskList, ui, storage);

        //return "Smith heard: " + string;
    }
}
package main;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.command.Command;
import main.exception.DukeException;
import main.parser.Parser;
import main.storage.Storage;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Duke application.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class Duke extends Application {
    private final Ui ui;
    private final Scanner sc;
    private final TaskList tasks;
    private boolean hasCommand;

    /**
     * Constructs the duke application.
     */
    public Duke() {
        sc = new Scanner(System.in);
        hasCommand = false;
        tasks = new TaskList();
        ui = new Ui();
    }

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked(event -> {
            dialogContainer.getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction(event -> {
            dialogContainer.getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty()
                .addListener(observable -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Initialises the duke application.
     */
    public void initialise() {
        try {
            hasCommand = true;
            ui.printGreeting();
            Storage.setTasks(tasks);

            while (hasCommand) {
                try {
                    String[] input = ui.getInput(sc);
                    Command command = Parser.parse(input);
                    command.execute(ui, tasks);

                    hasCommand = command.hasCommand();
                } catch (DukeException e) {
                    ui.printExceptionMessage(e);
                }
            }

            Storage.write(tasks);
        } catch (IOException e) {
            ui.printError();
        } finally {
            ui.printExit();
        }
    }

    public static void main(String[] args) {
        new Duke().initialise();
    }
}

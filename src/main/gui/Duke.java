package main.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
import javafx.scene.shape.Circle;
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
    private final Image userImage;
    private final Image dukeImage;
    private final Ui ui;
    private final TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    /**
     * Constructs the duke application.
     */
    public Duke() {
        tasks = new TaskList();
        ui = new Ui();
        userImage = new Image(
                this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(
                this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    @Override
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        ImageView dukeImageView = new ImageView(dukeImage);

        dukeImageView.setClip(new Circle(50, 50, 50));

        DialogBox dukeGreeting = DialogBox
                .getDukeDialog(new Label(ui.printGreeting()), dukeImageView);

        dukeGreeting.setSpacing(10);

        dialogContainer.getChildren().addAll(dukeGreeting);

        try {
            Storage.setTasks(tasks);
        } catch (IOException e) {
            dukeImageView = new ImageView(dukeImage);

            dukeImageView.setClip(new Circle(50, 50, 50));

            DialogBox dukeError = DialogBox
                    .getDukeDialog(new Label(ui.printError()), dukeImageView);

            dukeError.setSpacing(10);

            dialogContainer.getChildren().addAll(dukeError);
        }

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty()
                .addListener(observable -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        String output;
        boolean hasCommand = true;

        if (input.equals("")) {
            return;
        }

        Insets padding = new Insets(10, 0, 10, 0);

        ImageView userImageView = new ImageView(userImage);
        ImageView dukeImageView = new ImageView(dukeImage);

        userImageView.setClip(new Circle(50, 50, 50));
        dukeImageView.setClip(new Circle(50, 50, 50));

        String[] splitInput = input.trim().split(" ", 2);

        try {
            Command command = Parser.parse(splitInput);
            output = command.execute(ui, tasks);
            hasCommand = command.hasCommand();
        } catch (DukeException e) {
            output = e.getMessage();
        }

        Label userText = new Label(input);
        Label dukeText = new Label(output);

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImageView);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukeImageView);

        userDialog.setPadding(padding);
        dukeDialog.setPadding(padding);

        userDialog.setSpacing(10);
        dukeDialog.setSpacing(10);

        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();

        if (!hasCommand) {
            try {
                Storage.write(tasks);
            } catch (IOException e) {
                DialogBox dukeError = DialogBox
                        .getDukeDialog(new Label(ui.printError()), dukeImageView);
                dialogContainer.getChildren().addAll(dukeError);
            }
            Platform.exit();
        }
    }
}

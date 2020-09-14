package main.gui;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
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
import main.exception.InvalidOptionException;
import main.exception.StuffException;
import main.parser.Parser;
import main.storage.Storage;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Stuff application.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.2
 */
public class Stuff extends Application {
    private static final Random DELAY_GENERATOR = new Random();
    private final Image userImage;
    private final Image stuffImage;
    private final Image icon;
    private final Ui ui;
    private final TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    /**
     * Constructs the stuff application.
     */
    public Stuff() {
        tasks = new TaskList();
        ui = new Ui();
        userImage = new Image(
                this.getClass().getResourceAsStream("/images/Peter.jpg"));
        stuffImage = new Image(
                this.getClass().getResourceAsStream("/images/Stuff.jpg"));
        icon = new Image(
                this.getClass().getResourceAsStream("/images/StuffIcon.jpg"));
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

        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Stuff");
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

        ImageView stuffImageView = new ImageView(stuffImage);

        stuffImageView.setClip(new Circle(50, 50, 50));

        DialogBox stuffGreeting = DialogBox
                .getStuffDialog(new Label(ui.printGreetingMessage()), stuffImageView);

        dialogContainer.getChildren().addAll(stuffGreeting);

        try {
            Storage.setTasks(tasks);
        } catch (IOException e) {
            stuffImageView = new ImageView(stuffImage);

            stuffImageView.setClip(new Circle(50, 50, 50));

            DialogBox stuffDialog = DialogBox
                    .getStuffDialog(new Label(ui.printErrorMessage()), stuffImageView);

            dialogContainer.getChildren().addAll(stuffDialog);
        } catch (InvalidOptionException e) {
            stuffImageView = new ImageView(stuffImage);

            stuffImageView.setClip(new Circle(50, 50, 50));

            DialogBox stuffDialog = DialogBox
                    .getStuffDialog(new Label(e.getMessage()), stuffImageView);

            dialogContainer.getChildren().addAll(stuffDialog);
        }

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty()
                .addListener(observable -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        String output;
        boolean isEmptyInput = input.equals("");
        boolean hasCommandAfter = true;

        if (isEmptyInput) {
            return;
        }

        ImageView userImageView = new ImageView(userImage);
        ImageView stuffLoadingImageView = new ImageView(stuffImage);
        ImageView stuffImageView = new ImageView(stuffImage);

        userImageView.setClip(new Circle(50, 50, 50));
        stuffLoadingImageView.setClip(new Circle(50, 50, 50));
        stuffImageView.setClip(new Circle(50, 50, 50));

        String[] splitInput = input.trim().split(" ");

        try {
            Command command = Parser.parse(splitInput);
            output = command.execute(ui, tasks);
            hasCommandAfter = command.hasCommandAfter();
        } catch (StuffException e) {
            output = e.getMessage();
        }

        Label userText = new Label(input);
        Label stuffText = new Label("...");

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImageView);
        DialogBox stuffDialog = DialogBox.getStuffDialog(stuffText, stuffImageView);

        dialogContainer.getChildren().addAll(userDialog, stuffDialog);
        userInput.clear();

        String finalOutput = output;
        CompletableFuture
                .delayedExecutor(
                        DELAY_GENERATOR.nextInt(500) + 250,
                        TimeUnit.MILLISECONDS
                )
                .execute(() -> Platform.runLater(() -> {
                    stuffText.setText(finalOutput);
                }));

        if (!hasCommandAfter) {
            try {
                Storage.write(tasks);
            } catch (IOException e) {
                DialogBox stuffError = DialogBox
                        .getStuffDialog(new Label(ui.printErrorMessage()), stuffImageView);

                dialogContainer.getChildren().addAll(stuffError);
            }

            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS)
                    .execute(Platform::exit);
        }
    }
}

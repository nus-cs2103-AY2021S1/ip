package main.java;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Scanner;

/**
 * Represents a robot who can help the user to make todo list.
 * A <code>Duke</code> object is an instance of such robots.
 */
public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().
            getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().
            getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("./command.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * launch the Duke application, initialize the robot.
     */
    public void run() {
        ui.start();
        boolean flag = true;
        Parser parser = new Parser(tasks, true);
        while(flag) {
            String input = sc.nextLine();
            System.out.println(parser.handleCommand(input));
            flag = parser.getFlag();
        }
    }

    /**
     * Main method of the project, launch the project.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./command.txt").run();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        mainLayout.setPrefSize(500.0, 800.0);

        scrollPane.setPrefSize(490, 750);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(68.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        dialogContainer.heightProperty().
                addListener((observable -> scrollPane.setVvalue(1.0)));

        scene = new Scene(mainLayout);

        Label greet = new Label(ui.greet());

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greet, new ImageView(duke)));

        stage.setScene(scene);
        stage.show();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}


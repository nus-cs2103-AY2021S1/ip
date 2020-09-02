package duke;

import java.awt.desktop.SystemSleepEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Duke extends Application {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private AppParser appParser;
    private boolean isTextFileExist;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/img1.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/img2.jpg"));

    /**
     * Constructs a Duke object
     * @throws FileNotFoundException fileNotFoundException
     */
    public Duke() throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/main.java.duke.txt");
        appParser = new AppParser();
        try {
            tasks = new TaskList(TaskList.readTextFile2List(storage.load()));
            isTextFileExist = true;
        } catch (Exception e) {
            tasks = new TaskList();
            isTextFileExist = false;
        }
    }

    /**
     * Constructs a Duke object
     * @param filePath the file path
     * @throws FileNotFoundException fileNotFoundException
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(TaskList.readTextFile2List(storage.load()));
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Overrides the start method
     * @param stage stage
     */
    @Override
    public void start(Stage stage){
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(AppUi.getLogo()
                        + (isTextFileExist ? "" : "no ./data/main.java.duke.txt found")), new ImageView(duke))
        );

        stage.setScene(scene);
        stage.show();
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * @throws IOException for handling file
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Gets response for user input
     * @param input user input
     * @return the response for user input
     * @throws IOException for handling file
     */
    public String getResponse(String input) throws IOException {
        return appParser.appParse(input, tasks);
    }

    /**
     * Runs the Duke app
     * @throws IOException exception for reading files
     */
    public void run() throws IOException {
        ui.showWelcomeMessage();
        String inputCommand;
        Scanner sc = new Scanner(System.in);
        int condition = 1;
        while (condition == 1) {
            inputCommand = sc.nextLine();
            condition = parser.parse(inputCommand, tasks);
        }
        Checker.checkAndPrint(tasks);
        storage.writeFile(tasks);
    }

    /**
     * Drives the Duke app
     * @param args parameter for main method
     * @throws IOException exception for reading files
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/main.java.duke.txt").run();
    }
}

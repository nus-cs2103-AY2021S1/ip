package dobby;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class which begins the running of the chat bot
 */
public class Dobby extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean isInitialized = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image dobby = new Image(this.getClass().getResourceAsStream("/images/DaDobby.jpeg"));

    public Dobby() {
    }

    /**
     * Takes in user input as long as user gives and terminated when user
     * enter bye
     */
    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = ui.getInput();
            try {
                ui.reply(parser.getMessage(text));
            } catch (DobbyException e) { // prints error message
                ui.reply(e.getMessage());
            }
            if (text.equals("bye")) { // terminates program after bye command
                storage.rewriteFile();
                System.exit(0);
            }
        }
    }

    /**
     * Loads the dobbylist storage file and initializes other components of package
     */
    public void initialize() {
        File dobbyFile = new File("../dobbylist.txt");

        try {
            if (!dobbyFile.exists()) {
                dobbyFile.getParentFile().mkdirs(); // Will create parent directories if not exists
                dobbyFile.createNewFile();
                FileOutputStream s = new FileOutputStream(dobbyFile, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tasks = new TaskList();
        this.parser = new Parser(this.tasks);
        this.storage = new Storage(dobbyFile.getPath(), tasks);
        this.ui = new Ui();
        this.storage.readFile();
        isInitialized = true;
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        Dobby dobby = new Dobby();
        dobby.initialize();
        dobby.run();
    }


    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Arial", 50));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        Label hiWorld = new Label("Hi World!"); // Creating a new Label control
        hiWorld.setFont(new Font("Arial", 30));
        Scene scene2 = new Scene(hiWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        stage.setScene(scene2);

        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(20);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Dobby");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(490, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(440.0);

        sendButton.setPrefWidth(60.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(this.ui.greet()), new ImageView(dobby))
        );
        dialogContainer.setBackground(
                new Background(
                        new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        //Scroll down to the end every time dialogContainer's height changes.
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dobby))
        );
        dialogContainer.setBackground(
                new Background(
                        new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String response;
        if (!isInitialized) {
            this.initialize();
        }
        try {
            response = this.parser.getMessage(input);
        } catch (DobbyException e) {
            response = e.getMessage();
        }
        return response;
    }
}

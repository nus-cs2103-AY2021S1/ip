package willy.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import willy.store.TaskStore;
import willy.task.TaskList;
import willy.exceptions.WillyException;
import willy.task.Task;
import willy.command.Parser;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy extends Application {

    public static TaskStore storage;
    private static String lastGreeting = "bye";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\___/ ILLY ~(^-^)~\n"
            + "\tYour personal life secretary\n";
    private static boolean isOnJavaFX;

    // For JavaFX
    private static String introGUI = " __       ___        __\n"
            + " \\  \\    /    \\     /  /\n"
            + "  \\  \\  /  /\\  \\  /  /\n"
            + "   \\  \\/  /  \\  \\/  /\n"
            + "    \\___/     \\__/ ILLY ~(^-^)~\n"
            + "    Your personal life secretary\n";
    private Label userInput = new Label();
    private Text botResponse = new Text(provideHelp());
    private TextField inputField = new TextField();
    private Button enterButton = new Button("Enter");
    private Button clearButton = new Button("Clear");

    private String provideHelp() {
        String commands = "Commands:" + "\n" + "1. todo [TASK]"
                + "\n" + "2. deadline [TASK] /by [DATE] [TIME]"
                + "\n" + "3. event [TASK] /at [DATE] [TIME]"
                + "\n" + "4. done [TASK NUMBER]" + "\n" + "5. delete [TASK NUMBER]"
                + "\n" + "6. edit [TASK NUMBER] > [TASK DETAILS]"
                + "\n" + "7. find [KEYWORD(s)]" + "\n" + "8. list"
                + "\n" + "Type 'help' for more info on each command";
        return commands;
    }

    public Willy() {
        this.isOnJavaFX = false;
        System.out.println(logo);
        Greet startDuke = new Greet();
        System.out.println(startDuke); // prints out intro
        storage = new TaskStore();
        storage.createFile();
    }

    // Put together the interactionBox which consist of the displaying of user inputs and bot's response
    private VBox interactionBoxCreator() {

        Rectangle userInputContainer = new Rectangle(330,40);
        userInputContainer.setFill(Color.rgb(180, 157, 253));
        StackPane userInputStack = new StackPane();
        userInput.setTextFill(Color.WHITE);
        // Solution below from https://stackoverflow.com/questions/12341672/make-portion-of-a-text-bold-in-a-javafx-label-or-text
        userInput.setStyle("-fx-font-weight: bold");
        userInputStack.getChildren().addAll(userInputContainer, userInput);
        userInputStack.setAlignment(userInput, Pos.CENTER_RIGHT);

        // Responsible for BotResponse
        // Solution adapted from https://www.jackrutorial.com/2020/04/how-to-set-background-color-ofscrollpane-in-javafx.html
        ScrollPane botResponseContainer = new ScrollPane();
        botResponseContainer.setStyle("-fx-background: #EBE9F7; -fx-border-color: #262626;");
        botResponseContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        botResponseContainer.setPrefViewportHeight(160);
        botResponseContainer.setPrefViewportWidth(180);
        TextFlow text = new TextFlow(botResponse);
        text.setPrefWidth(300);
        text.setPadding(new Insets(10, 5, 0, 5));
        botResponseContainer.setContent(text);

        // Bot and User Interaction Container
        VBox interactionBox = new VBox();
        interactionBox.setPadding(new Insets(5, 20, 10, 30));
        interactionBox.setSpacing(10);
        interactionBox.setMinHeight(200);
        interactionBox.getChildren().addAll(userInputStack, botResponseContainer);

        return interactionBox;
    }

    // Put together the input Container which consist of the text field and the enter & clear button
    private HBox inputContainerCreator() {
        // Settle buttons
        inputField.setPrefWidth(220);
        inputField.setPromptText("State tasks to track");

        // Putting together input components
        HBox inputContainer = new HBox();
        inputContainer.setSpacing(10);
        inputContainer.setPadding(new Insets(5, 20, 20, 30));
        inputContainer.getChildren().addAll(inputField, enterButton, clearButton);

        return inputContainer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        // normal code to start Willy
        new Willy(true);
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);


        // JavaFX code
        stage.setTitle("Willy"); // Stage Name

        // Responsible for Willy Greetings & Input Section
        Label willy = new Label(introGUI);
        Greet startDuke = new Greet();
        Label botCommand = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);

        // Putting together Intro Components of the Bot which consist of the profile of the bot and it's greetings
        // Creating of profile picture code is adapted from https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
            // Responsible for Willy image
            FileInputStream inputStream = new FileInputStream("D:\\NUS\\Y2Sem1\\CS2103T\\Indiv_Proj\\ip\\asset\\willy" +
                    ".png");
            Image image = new Image(inputStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(70);
            imageView.setFitWidth(60);
            StackPane imageContainer = new StackPane();
            imageContainer.getChildren().addAll(new Circle(48), imageView);

            // Combine all the components in introContainer
            HBox willyIntro = new HBox();
            willyIntro.setSpacing(10);
            willyIntro.setPadding(new Insets(20, 20, 0, 30));
            willyIntro.getChildren().addAll(imageContainer, willy);

        // Handles Actions of Buttons
        enterButton.setOnAction(action -> {
            String message = inputField.getText();
            userInput.setText(message + "\t   ");
            inputField.clear();
            botResponse.setText(parser.parse(message, true)); // Returns Response
        });
        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        // Combine everything together
        VBox vbox = new VBox(); // Positions components in a vertical column
        vbox.getChildren().addAll(willyIntro, botCommand, interactionBoxCreator(), inputContainerCreator());

        StackPane layout = new StackPane();
        layout.getChildren().addAll(vbox);

        // Build & Show Scene
        Scene scene = new Scene(layout, 380, 480);
        stage.setScene(scene);
        stage.show(); // To display stage to users
    }

    public Willy(boolean boo) {
        this.isOnJavaFX = boo;
        storage = new TaskStore();
        storage.createFile();
    }

    public static String getLastGreeting() {
        return lastGreeting;
    }

    public static String response(String message) {
        return "\n" + message + "\n" ;
    }

    public static void main(String[] args) throws WillyException {

        new Willy();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        while (input.hasNext()) {

            String message = input.nextLine();

            // check when to end the bot
            if (message.equals(lastGreeting)) {
                Greet endGreeting = new Greet(message);
                System.out.println(endGreeting);
                break;
            }

            parser.parse(message, false);
        }
        input.close();
    }
}

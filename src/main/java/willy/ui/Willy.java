package willy.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

    private static TaskStore storage;
    private static String lastGreeting = "bye";
    private static String style = "\t_________________________________________________________________\n";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\___/ ILLY ~(^-^)~\n"
            + "\tYour personal life secretary\n";
    private static String introGUI = " __       ___        __\n"
            + " \\  \\    /    \\     /  /\n"
            + "  \\  \\  /  /\\  \\  /  /\n"
            + "   \\  \\/  /  \\  \\/  /\n"
            + "    \\___/     \\__/ ILLY ~(^-^)~\n"
            + "    Your personal life secretary\n";
    private static boolean isOnJavaFX;

    public Willy() {
        this.isOnJavaFX = false;
        System.out.println(logo);
        Greet startDuke = new Greet();
        System.out.println(startDuke); // prints out intro
        storage = new TaskStore();
        storage.createFile();
    }

    public Willy(boolean boo) {
        this.isOnJavaFX = boo;
        storage = new TaskStore();
        storage.createFile();
    }

    public static String getLastGreeting() {
        return lastGreeting;
    }

    public static String getStyle() {
        return style;
    }

    public static String response(String message) {
        return "\n\t" + message + "\n" + style;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        // normal code to start Willy
        new Willy(true);
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        // JavaFX code
        stage.setTitle("Willy"); // Stage Name

        // Responsible for Willy image (NOT FUNCTIONING)
        // Solution below adapted from https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
        FileInputStream inputStream = new FileInputStream("asset/willy.png");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        StackPane imageContainer = new StackPane();
        imageContainer.getChildren().addAll(new Circle(48), imageView);


        // Responsible for Willy Greetings & Input Section
        Label willy = new Label(introGUI);
        Greet startDuke = new Greet();
        Label botCommand = new Label(startDuke.toString());
        Label userInput = new Label();
        Text botResponse = new Text();
        willy.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("State tasks to track");
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");

        // Handles Actions of Buttons
        enterButton.setOnAction(action -> {
                String message = inputField.getText();
                userInput.setText("\t   " + message);
                inputField.clear();
                botResponse.setText(parser.parse(message, true)); // Returns Response
        });
        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        // Putting together Intro Components
        HBox willyIntro = new HBox();
        willyIntro.setSpacing(10);
        willyIntro.setPadding(new Insets(20, 20, 0, 30));
        willyIntro.getChildren().addAll(imageContainer, willy);

        // Putting together input components
        HBox inputContainer = new HBox();
        inputContainer.setSpacing(10);
        inputContainer.setPadding(new Insets(5, 20, 20, 30));
        inputContainer.getChildren().addAll(inputField, enterButton, clearButton);

        // Putting together response components
        Rectangle typingContainer = new Rectangle(330,48);
        typingContainer.setFill(Color.rgb(203, 202, 254));
        StackPane inputStack = new StackPane();
        inputStack.getChildren().addAll(typingContainer, inputContainer);
        Rectangle userInputContainer = new Rectangle(330,20);
        userInputContainer.setFill(Color.rgb(180, 157, 253));
        StackPane userInputStack = new StackPane();
        userInput.setTextFill(Color.WHITE);
        userInputStack.getChildren().addAll(userInputContainer, userInput);
        userInputStack.setAlignment(userInput, Pos.CENTER_LEFT);

        // Responsible for BotResponse
        ScrollPane botResponseContainer = new ScrollPane();
        botResponseContainer.setFitToWidth(true);
        botResponseContainer.setMaxHeight(200);
        botResponseContainer.setMinViewportHeight(210);
        botResponseContainer.setContent(botResponse);


        // Combine everything together
        VBox vbox = new VBox(); // Positions components in a vertical column
        vbox.getChildren().addAll(willyIntro, botCommand, inputStack, userInputStack, botResponseContainer);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(vbox);

        // Build & Show Scene
        Scene scene = new Scene(layout, 380, 480, Color.BLACK);
        stage.setScene(scene);
        stage.show(); // To display stage to users
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

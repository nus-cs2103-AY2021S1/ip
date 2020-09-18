package willy.ui;

import static javafx.application.Platform.exit;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import willy.store.TaskStore;
import willy.task.TaskList;
import willy.task.Task;
import willy.command.Parser;

/**
 * A bot that records tasks for people and keeps track of it for them.
 */
public class Willy extends Application {

    private static TaskStore storage;
    private static String lastGreeting = "bye";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\___/ ILLY ~(^-^)~\n"
            + "\tYour personal life secretary\n";
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

    public static String response(String message) {
        return "\n" + message + "\n";
    }

    @Override
    public void start(Stage stage) throws Exception {
        // normal code to start Willy
        new Willy(true);
        ArrayList<Task> listOfTask = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        // JavaFX code
        stage.setTitle("Willy"); // Stage Name
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");
        TextField inputField = new TextField();
        JAVAFXInteractionBox interactionBox = new JAVAFXInteractionBox();

        enterButton.setOnAction(action -> {
            String message = inputField.getText();
            JAVAFXInteractionBox.userInput.setText(message + "\t   ");
            inputField.clear();
            JAVAFXInteractionBox.botResponse.setText(parser.parseCommand(message, true)); // Returns Response
            if (message.equals(Willy.getLastGreeting())) {
                exit();
            }
        });

        // Solution below adapted from https://stackoverflow
        // .com/questions/13880638/how-do-i-pick-up-the-enter-key-being-pressed-in-javafx2
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String message = inputField.getText();
                JAVAFXInteractionBox.userInput.setText(message + "\t   ");
                inputField.clear();
                JAVAFXInteractionBox.botResponse.setText(parser.parseCommand(message, true)); // Returns Response
                if (message.equals(Willy.getLastGreeting())) {
                    exit();
                }
            }
        });

        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        // Combine everything together
        VBox ui = new VBox(); // Positions components in a vertical column
        ui.getChildren().addAll(JAVAFXIntroContainer.createIntroContainer(),
                interactionBox.interactionBoxCreator(),
                JAVAFXInputContainer.inputContainerCreator(inputField, enterButton, clearButton));

        StackPane layout = new StackPane();
        layout.getChildren().addAll(ui);

        // Build & Show Scene
        Scene scene = new Scene(layout, 380, 480);
        stage.setScene(scene);
        stage.show(); // To display stage to users

    }

    public static void main(String[] args) {

        new Willy();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> listOfTask = storage.retrieveStorage();
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

            parser.parseCommand(message, false);
        }
        input.close();
    }
}

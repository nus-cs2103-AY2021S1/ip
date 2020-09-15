package willy.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private static String style = "\t_____________________________________________________________________________\n";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\___/ ILLY ~(^-^)~\n" +
            "\tYour personal life secretary\n";
    private static String introGUI = "\t __       ___        __\n"
            + "\t \\  \\    /    \\     /  /\n"
            + "\t  \\  \\  /  /\\  \\  /  /\n"
            + "\t   \\  \\/  /  \\  \\/  /\n"
            + "\t    \\___/     \\__/ ILLY ~(^-^)~\n" +
            "\t    Your personal life secretary\n";
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
        return  style + "\t" + message + "\n" + style;
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
        // Solution below adapted from https://www.tutorialspoint.com/javafx/javafx_images.htm
        FileInputStream inputstream = new FileInputStream("asset/Willy.jpg");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(100);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);
        Group willyImage = new Group(imageView);

        // Responsible for Willy Greetings & Input Section
        Label willy = new Label(introGUI);
        Greet startDuke = new Greet();
        Label botCommand = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("State tasks to track");
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");

        // Handles Actions of Buttons
        enterButton.setOnAction(action -> {
                String message = inputField.getText();
                inputField.clear();
                botCommand.setText(parser.parse(message, true)); // Returns Response
        });
        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        // Putting together Layout Components
        HBox willyIntro = new HBox();
        willyIntro.setSpacing(10);
        willyIntro.getChildren().addAll(willyImage, willy);
        HBox hbox = new HBox(); // Positions components in a horizontal row
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 20, 5, 30));
        hbox.getChildren().addAll(inputField, enterButton, clearButton);
        VBox vbox = new VBox(); // Positions components in a vertical column
        vbox.getChildren().addAll(willyIntro, botCommand, hbox);

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

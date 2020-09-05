package willy.ui;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private static String style = "\t_____________________________________________________________________________\n";
    private static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\____/ ILLY ~(^-^)~\n";
    private static String introGUI = "\t __       ___        __\n"
            + "\t \\  \\    /    \\     /  /\n"
            + "\t  \\  \\  /  /\\  \\  /  /\n"
            + "\t   \\  \\/  /  \\  \\/  /\n"
            + "\t    \\___/     \\__/ ILLY ~(^-^)~\n" +
            "\t    Your personal life secretary\n";
    private static boolean isOnJavaFX;

    public Willy() {
        this.isOnJavaFX = false;
        System.out.println(logo + "    Your personal life secretary");
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

    public static String getStyle() {
        return style;
    }

    public static String getLogo() {
        return logo;
    }

    public static String getIntroGUI() {
        return introGUI;
    }

    public static boolean isOnJavaFX() {
        return isOnJavaFX;
    }

    public static String response(String message) {
        return  style + "\t" + message + "\n" + style;
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
        Label changingCommand = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("State tasks to track");
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");

        // Handles Actions of Buttons
        enterButton.setOnAction(action -> {
                String message = inputField.getText();
                inputField.clear();
                changingCommand.setText(parser.parse(message, true)); // Returns Response
        });
        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        // Putting together Layout Components
        HBox hbox = new HBox(); // Positions components in a horizontal row
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 20, 5, 30));
        hbox.getChildren().addAll(inputField, enterButton, clearButton);
        VBox vbox = new VBox(); // Positions components in a vertical column
        vbox.getChildren().addAll(willy, hbox, changingCommand);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(vbox);

        // Build & Show Scene
        Scene scene = new Scene(layout, 500, 450, Color.BLACK);
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
            parser.parse(message, false);
        }
        input.close();
    }
}

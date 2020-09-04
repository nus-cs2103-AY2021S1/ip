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
    private static String style = "\t________________________________________________________________\n";
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
    private boolean isOnJavaFX;

    public Willy() {
        this.isOnJavaFX = false;
        System.out.println(logo + "    Your personal life secretary");
        Greet startDuke = new Greet();
        // prints out intro
        System.out.println(startDuke);
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

    public boolean isOnJavaFX() {
        return isOnJavaFX;
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

        Label willy = new Label(introGUI); // Creating a new Label control
        Greet startDuke = new Greet();
        Label changingCommands = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("State tasks to track");
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");

        enterButton.setOnAction(action -> {
                String message = inputField.getText();
                inputField.clear();
                changingCommands.setText(parser.javaFXParse(message)); // Returns Response
        });
        clearButton.setOnAction(action -> {
            inputField.clear();
        });

        HBox hbox = new HBox(); // Positions components in a horizontal row
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 20, 5, 30));
        hbox.getChildren().addAll(inputField, enterButton, clearButton);
        VBox vbox = new VBox(); // Positions components in a vertical column
        vbox.getChildren().addAll(willy, hbox, changingCommands);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(vbox);

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
            parser.parse(message);
        }
        input.close();
    }
}

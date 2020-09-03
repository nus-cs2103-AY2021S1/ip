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
    public static String style = "\t________________________________________________________________\n";
    public static String logo = "__       ____       __\n"
            + "\\  \\    /    \\    /  /\n"
            + " \\  \\  /  /\\  \\  /  /\n"
            + "  \\  \\/  /  \\  \\/  /\n"
            + "   \\____/     \\____/ ILLY ~(^-^)~\n";
    public static String introGUI = "\t __       ___        __\n"
            + "\t \\  \\    /    \\     /  /\n"
            + "\t  \\  \\  /  /\\  \\  /  /\n"
            + "\t   \\  \\/  /  \\  \\/  /\n"
            + "\t    \\___/     \\__/ ILLY ~(^-^)~\n" +
            "\t    Your personal life secretary\n";
    public boolean isOnJavaFX;

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
    @Override
    public void start(Stage stage) throws Exception {

        // normal code to start Willy
        new Willy(true);
        ArrayList<Task> listOfTask  = storage.retrieveStorage();
        TaskList list = new TaskList(listOfTask, storage);
        Parser parser = new Parser(list);

        // JavaFX code
        stage.setTitle("Willy"); // Stage Name

//        BorderPane border = new BorderPane();
//        border.setPadding(new Insets(10,20, 20,20));

        //Creating a GridPane container
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(5);
//        grid.setHgap(5);

        Label willy = new Label(introGUI); // Creating a new Label control
        Greet startDuke = new Greet();
        Label changingCommands = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("State tasks to track");
//        GridPane.setConstraints(inputField, 0, 0);
//        grid.getChildren().add(inputField);
        Button enterButton = new Button("Enter");
//        GridPane.setConstraints(button, 1, 0);
//        grid.getChildren().add(button);
        Button clearButton = new Button("Clear");
//        GridPane.setConstraints(clear, 1, 1);
//        grid.getChildren().add(clear);


        enterButton.setOnAction(action -> {
//            System.out.println(inputField.getText());
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
//        vbox.setAlignment(Pos.CENTER);

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

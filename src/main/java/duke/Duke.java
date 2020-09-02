package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A chatbot that helps the user keep track of todo, deadline and event tasks 
 * by storing them in a list.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    static final String filepath = "duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    
    public Duke() {
        
    }
    
    /**
     * Constructs a Duke object and initialises the TaskList, Ui and Storage.
     * @param filepath location of the save file containing the list of tasks.
     */
    public Duke (String filepath) {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            storage.pullList(tasks);
        } catch (Exception e) {
            ui.printError(e);
        }
    }

    /**
     * Starts running Duke by printing welcome.
     * Utilises Ui to scan for the next user input and the Parser to parse it.
     * Executes the command determined by the user's input.
     * Stops running if the next input is "bye".
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String nextCommand = ui.readCommand();
                Command c = Parser.parse(nextCommand);
                c.execute(tasks, ui, storage);
                isRunning = c.continueRunning();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
    
    public static void main (String[] args) {
        new Duke(filepath).run();
    }

    @Override
    public void start(Stage stage) {
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

        stage.setScene(scene);
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
        stage.show();
        // more code to be added here later
    }

    
    
    

//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    
}
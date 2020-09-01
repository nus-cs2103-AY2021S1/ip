package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a Duke class.
 */
public class Duke {

//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }

    /** Storage for reading and writing all tasks */
    private Storage storage;

    /** Task list containing tasks */
    private TaskList tasks;

    /** Handles printing of user interaction */
    private final Ui ui = new Ui();

    private static final String FILEPATH = "data/duke.txt";


    public Duke() {
        try {
            storage = new Storage(FILEPATH);
            tasks = new TaskList(storage.read());
        } catch (DukeException | IOException ex) {
            ui.showLoadingError();
            tasks = new TaskList(tasks.getTasks());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printGreetings();
        while (ui.hasMoreInput()) {
           try {
               String userInput = ui.readCommand();
               Command command = Parser.parseCommands(userInput);
               command.execute(this.tasks, this.storage, this.ui);
           } catch (DukeException | IOException ex) {
               System.out.println(ex.getMessage());
           }
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Executes the main program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    String getResponse(String input) {
       try {
           Command c = Parser.parseCommands(input);
           return c.execute(this.tasks, this.storage, this.ui); //task and storage alr null
       } catch (IOException | DukeException e) {
           return e.getMessage();
       }
    }
}





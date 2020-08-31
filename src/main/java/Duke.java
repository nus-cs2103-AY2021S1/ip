package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import main.java.commands.Command;
import main.java.exceptions.DukeException;
import main.java.tasklist.TaskList;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.displayErrorMessage(e);
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Label jianhan = new Label("Jianhan Cute???"); // Creating a new Label control

        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        Scene scene2 = new Scene(jianhan); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.setScene(scene2);
        stage.show(); // Render the stage.
    }

    /**
     * Run a Duke object while isExit is not changed to true
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/data.txt").run();
    }

}

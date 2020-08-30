package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a chatbot that can perform specified commands requested by clients.
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor of Duke class
     *
     * @param filePath address of file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke program
     *
     * @throws FileNotFoundException If the file path cannot be found
     */
    public void run() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String userInput = sc.nextLine();
            isExit = Parser.execute(tasks, ui, storage, userInput);
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke("./data/duke.txt").run();
    }
}


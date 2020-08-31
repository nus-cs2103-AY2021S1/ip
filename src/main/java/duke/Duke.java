package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point of the program.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList<Task> tasks;
    private Ui ui;

    /**
     * Instantiates a Duke object.
     * @param filePath the directory to store data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList<>(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public Duke() {
        this("data/duke.txt");
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs the entire program with the main logic.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.welcome();
        boolean exitProgram = false;
        while (!exitProgram && sc.hasNextLine()) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.writeToFile(tasks);
                exitProgram = c.isExitProgram();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Starts the Duke program.
     * @param args user input that's not needed here
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

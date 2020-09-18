package seedu.duke;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke program implements an application that simulates a smart task manager with the name Duke.
 * The program takes in user inputs with various formats and process them as tasks to be finished.
 *
 */

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the class.
     * Returns a new Duke object.
     * @param filePath The path or intended path of the data management file
     * @throws IOException If data management file fails to be created or accessed.
     * @throws DukeException If other errors occur.
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructor with no arguments.
     * @throws IOException
     * @throws DukeException
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage("/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt");
        tasks = new TaskList(storage.load());
    }




    /**
     * Runs the Duke program.
     * Prompt user to key in their input with a welcome message
     * @throws DukeException If user input is of incorrect format or other unidentified error occurs
     * @throws IOException If fails to take user input or create/access data management file
     */

    public void run() throws DukeException, IOException {
        //welcoming message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Duke at your service. Please name your request."
                + "\nAll dates should be in the form of YYYY-MM-DD: ");

        // Take user Input
        ui.takeUserInput(storage);

    }


    /**
     * Creates a new Duke object and runs the program
     * @param args
     * @throws DukeException
     * @throws IOException
     */

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt").run();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.
    }
}

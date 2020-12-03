package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
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
     * Constructor of Duke class.
     *
     * @param filePath address of file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke program.
     *
     * @throws FileNotFoundException If the file path cannot be found.
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

    /**
     * Gets message to UI display.
     *
     * @param input the input of user.
     * @return the reply to the specific command of users.
     * @throws FileNotFoundException If the file path cannot be found.
     */
    public String getResponse(String input) throws FileNotFoundException {
        return Parser.getUiReply(tasks, ui, storage, input);
    }

    /**
     * Shows Duke welcoming message.
     *
     * @return String welcoming greeting.
     */
    public String dukeGreeting() {
        return ui.showWelcome();
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke("./data/duke.txt").run();
    }
}


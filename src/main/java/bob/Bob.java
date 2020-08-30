package bob;

import java.io.IOException;

import bob.commands.Command;
import bob.common.Messages;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the task-managing ChatBot.
 */
public class Bob extends Application {
    private final Storage storage;
    private final Tasklist tasks;
    private final Ui ui;
    /**
     * Creates a Bob.
     *
     * @throws IOException If saved file can't be loaded.
     */
    public Bob() throws IOException {
        String filePath = System.getProperty("user.dir").endsWith("text-ui-test")
                ? "test.txt"
                : System.getProperty("user.dir").endsWith("ip")
                ? "data/bob.txt"
                // Creates a save file on the user's home directory if user is not in ip directory
                : System.getProperty("user.home") + "/bob.txt";
        Tasklist tempTasks;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tempTasks = new Tasklist(storage);
        } catch (BobException | IOException e) {
            System.out.println(Messages.LOADING_ERROR);
            tempTasks = new Tasklist();
        }
        this.tasks = tempTasks;
    }

    /**
     * Runs Bob.
     */
    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExited();
            } catch (IOException e) {
                ui.showUpdatingError();
            } catch (BobException e) {
                ui.showError(e);
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * The main environment where Bob runs.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //File path of saved task list
        try {
            new Bob().run();
        } catch (IOException e) {
            System.out.println(Messages.INVALID_PATHNAME);
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}

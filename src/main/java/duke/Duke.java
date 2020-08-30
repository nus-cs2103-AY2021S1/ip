package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for running bot.
 */
public class Duke extends Application {
    private static final String FILE_PATH = (System.getProperty("user.dir").endsWith("text-ui-test") 
        ? System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 13) 
            + "/data/duke.txt"
        : System.getProperty("user.dir") + "/data/duke.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        
    }
    
    /**
     * Constructs a Duke instance with the specified file name.
     * 
     * @param filePath String of file's path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method of programme.
     * 
     * @param args Command line arguments. Not needed for this programme
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}

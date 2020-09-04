import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private final Storage STORAGE;
    private final TaskList TASKS;
    private final Ui UI;

    // constructor
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        TASKS = new TaskList(STORAGE.loadTasks());
    }

    // empty constructor
    public Duke() {
        UI = new Ui();
        STORAGE = new Storage("src/main/java/tasks.txt");
        TASKS = new TaskList(STORAGE.loadTasks());
    }

    public void run() {

        UI.greet(STORAGE.createResult);

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                Command c = Parser.interpret(fullCommand);
                c.execute(TASKS, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showLine();
                UI.showError(e.getMessage());
                UI.showLine();
            }
        }
    }

    // copied from javaFx tutorial
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {

        // the argument to the constructor is the file path (relative) where Duke will read and write tasks given to it
        new Duke("src/main/java/tasks.txt").run();

    }

    public String getResponse(String input) {
        try {
            Parser.interpret(input).execute(this.TASKS, this.UI, this.STORAGE);
            return "Done!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    };
}

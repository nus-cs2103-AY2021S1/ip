import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the Duke bot.
 */
public class Duke extends Application {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a Duke bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, "duke");
    }

    /**
     * Runs the Duke bot program
     */
    public void run() {
        ui.startUp(taskList, storage);
        Parser.parseInput(taskList, storage);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        new Duke().run();
    }
}

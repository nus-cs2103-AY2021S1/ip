import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Duke is the application that the user is aware of.
 */
public class Duke extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke for GUI.
     */
    public Duke() {
        // path is set if GUI
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.initializeTasks());
    }

    /**
     * Creates a new Duke for CommandLineInterface.
     *
     * @param filePath file path where a file containing taskList from last execution of Duke is stored.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.initializeTasks());
    }

    /**
     * Starts a run of GUI.
     *
     * @param stage Display of application.
     */
    @Override
    public void start(Stage stage) {
        GraphicalUserInterface graphicalUserInterface = new GraphicalUserInterface(stage);
        ui = graphicalUserInterface;
        ui.showWelcome();

        TextField textField = graphicalUserInterface.getTextField();
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        Command c = Parser.parse(textField.getText());
                        c.execute(tasks, ui, storage);
                        if (c.isExit()) {
                            ui.showGoodbye();
                        }
                    } catch (DukeException | TaskException e) {
                        ui.showError(e.getMessage());
                    } finally {
                        textField.setText("");
                    }
                }
            }
        });
    }

    /**
     * Starts an execution of Duke on Command Line Interface.
     * There is a Welcome, a series of Commands and finally a Goodbye from Duke.
     */
    public void run() {
        CommandLineInterface commandLineInterface = new CommandLineInterface();
        ui = commandLineInterface;

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = commandLineInterface.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | TaskException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

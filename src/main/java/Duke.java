import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    // Constants

    // Attributes
    private final Storage storage;
    private final TaskList tasks;
    private Ui ui;

    // Constructor
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.read();
    }

    // Wrap text in lines

    // Scanner
    private final Scanner sc = new Scanner(System.in);

    /**
     * Runs the Duke bot.
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
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/todo.txt").run();
    }
}
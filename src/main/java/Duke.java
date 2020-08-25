import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Wayne Tan
 * @since Aug 2020
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke construction specifying a filePath to initialise the Storage, Ui and TaskList.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
//            ui.showError(e.getMessage());
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts to run Duke by showing welcome.
     * Uses Ui to scan input.
     * Uses Parser to parse the input.
     * Execute Command.
     * If the command is 'bye', loop terminates.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | ParseException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

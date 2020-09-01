/**
 * Represents a to do list chatbot called Duke.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke {
    /**
     * Storage object that handles reading and writing to local hard disk.
     */
    private Storage storage;
    /**
     * Task list that stores tasks.
     */
    private TaskList tasks;
    /**
     * UI object that handles interactions with the user.
     */
    private Ui ui;

    /**
     * Duke constructor.
     * @param filePath The path of the local copy where Duke saves list to.
     */
    public Duke(final String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException de) {
            ui.printLoadingError(de);
            tasks = new TaskList();
        }
    }

    /**
     * This method runs Duke the chatbot by taking in commands from the user.
     */
    public void run() {
        ui.printGreeting();

        String input;
        boolean isEnd = false;

        try {
            while (!isEnd) {
                input = ui.readCommand();
                isEnd = Parser.execute(tasks, ui, storage, input);
            }
        } catch (DukeException de) {
            ui.printLoadingError(de);
        }
    }

    /**
     * This is the main method which makes use of the run method.
     * @param args Unused
     */
    public static void main(final String[] args) {
        new Duke("data/duke.txt").run();
    }
}

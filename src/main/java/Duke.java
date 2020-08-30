import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor takes in file path to setup duke
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the Duke instance
     */
    public void run() {
        ui.welcome();
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Command.parse(command);
                c.execute(tasks, ui, storage);
                exit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();;
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
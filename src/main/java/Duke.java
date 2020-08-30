import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Command command;
    private Ui ui;

    /**
     * Returns a Duke application.
     * @param filePath Path of file that contains tasks.
     * @throws IOException If an error occurs while accessing/creating the
     * directory/file containing the tasks.
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        parser = new Parser();
        command = new Command();
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * @throws IOException If an error occurs while accessing/creating the
     * directory/file containing the tasks.
     */
    public void run() throws IOException {
        ui.showWelcome();
        command.receive(ui.readCommand());
        while (!command.exit()) {
            try {
                command.executeTask(parser, tasks, storage, ui);
            } catch (InvalidTaskArgumentException | InvalidDoneException | InvalidCommandException
                    | InvalidDeleteException | DateException e) {
                ui.showError(e.getMessage());
            } finally {
                command.receive(ui.readCommand());
            }
        }
        ui.showFarewell();
    }

    /**
     * Provides the entry point to the Duke application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke("data/tasks.txt").run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

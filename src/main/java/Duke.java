import java.io.IOException;

/**
 * Represents the backend of the Duke application. The backend consists of the
 * storage, task list, parser, and user interface components which are coordinated
 * by the command.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Command command;
    private Ui ui;

    /**
     * Constructs a Duke application with the specified location for
     * containing tasks.
     * @param filePath Path of the file that contains the tasks.
     * @throws IOException If an error occurs while accessing or creating the
     * directory or file containing the tasks.
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
     * Constructs a Duke application with the default location for
     * containing tasks.
     * @throws IOException If an error occurs while accessing or creating the
     * directory or file containing the tasks.
     */
    public Duke() throws IOException {
        this("data/tasks.txt");
    }

    /**
     * Runs the Duke application.
     * @throws IOException If an error occurs while accessing or creating the
     * directory or file containing the tasks.
     */
    public void run() throws IOException {
        ui.showWelcome();
        command.receiveUserCommand(ui.readCommand());
        while (!command.exit()) {
            try {
                command.executeUserCommand(parser, tasks, storage, ui);
            } catch (InvalidTaskArgumentException | InvalidDoneException | InvalidCommandException
                    | InvalidDeleteException | InvalidFindException | DateException e) {
                ui.showError(e.getMessage());
            } finally {
                command.receiveUserCommand(ui.readCommand());
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

    /**
     * Returns a response to the main window based on the user command received.
     * @param userCommand User command received by the Duke application.
     * @return Response to the main window.
     * @throws IOException If an error occurs while accessing or creating the
     * directory or file containing the tasks.
     */
    public String getResponse(String userCommand) throws IOException {
        try {
            command.receiveUserCommand(userCommand);
            return command.executeUserCommand(parser, tasks, storage, ui);
        } catch (InvalidTaskArgumentException | InvalidDoneException | InvalidCommandException
                | InvalidDeleteException | InvalidFindException | DateException e) {
            return ui.showError(e.getMessage());
        }
    }
}

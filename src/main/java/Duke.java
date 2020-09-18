import java.util.Scanner;

/**
 * Duke is the application that the user is aware of.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;

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

    public String getResponse(String text) throws DukeException, TaskException {
        Command c = Parser.parse(text);
        return Ui.formatMultiLine(c.execute(tasks, storage));
    }

    /**
     * Starts an execution of Duke on Command Line Interface.
     * There is a Welcome, a series of Commands and finally a Goodbye from Duke.
     */
    public void run() {
        System.out.println(Ui.formatResponse(Ui.getWelcome()));
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(sc.nextLine());
                System.out.println(Ui.formatResponse(c.execute(tasks, storage)));
                isExit = c.isExit();
            } catch (DukeException | TaskException ex) {
                System.out.println(Ui.formatResponse(ex.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

/**
 * Represents a to do list chatbot called Duke.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class Duke  {
    /**
     * Storage object that handles reading and writing to local hard disk.
     */
    private Storage storage;
    /**
     * Task list that stores tasks.
     */
    private TaskList taskList;
    /**
     * UI object that handles interactions with the user.
     */
    private Ui ui;

    private final String EXIT_STRING = "Bye bye!!! See you again next time :)";

    /**
     * Duke constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (DukeException de) {
            ui.printLoadingError(de);
            taskList = new TaskList();
        }
    }

    /**
     * Duke constructor.
     * @param filePath The path of the local copy where Duke saves list to.
     */
    public Duke(final String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (DukeException de) {
            ui.printLoadingError(de);
            taskList = new TaskList();
        }
    }

    /**
     * This method runs Duke the chatbot by taking in commands from the user.
     */
    public void run() {
        ui.printGreeting();

        String input;
        boolean isEnd = false;
        while (!isEnd) {
            input = ui.readCommand();
            String resultString;
            try {
                Command command = Parser.parse(input);
                resultString = command.execute(this.storage, this.ui, this.taskList);
            } catch (DukeException e) {
                resultString = e.getMessage();
            }
            isEnd = resultString.equals(EXIT_STRING);
        }
    }

    protected String getResponse(String input) {
        String resultString;
        try {
            Command command = Parser.parse(input);
            resultString = command.execute(this.storage, this.ui, this.taskList);
        } catch (DukeException e) {
            resultString = e.getMessage();
        }
        return resultString;
    }

    /**
     * This is the main method which makes use of the run method.
     *
     * @param args Unused
     */
    public static void main(final String[] args) {
        new Duke("data/duke.txt").run();
    }
}

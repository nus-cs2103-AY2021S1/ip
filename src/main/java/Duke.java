import javafx.application.Application;

/**
 * Encapsulates the Duke object, supports the function getResponse which returns a response when 
 * provided with a user input.
 */
public class Duke {

    /**
     * Represents the file path for the file that saves the task list.
     */
    public static String FILE_PATH = "myTaskList.txt";

    /**
     * Represents the storage object in charge of storing and loading data.
     */
    private final Storage storage;

    /**
     * Represents the taskList object in charge of maintaining the list of tasks.
     */
    private TaskList tasks;

    /**
     * Represents the ui object in charge of showing the response to the user.
     */
    private final Ui ui;

    /**
     * Represents whether the user wants to exit the application.
     */
    boolean isExit = false;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Takes in a user input and parses it to generate a command. Executes the command and returns the output
     * as a string.
     * 
     * @param input user input
     * @return a string representing the output.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return ui.formatResponse(c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Returns the duke logo and welcome message.
     * 
     * @return a string representation of the duke logo and welcome message. 
     */
    String showWelcome() {
        return ui.showWelcome();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
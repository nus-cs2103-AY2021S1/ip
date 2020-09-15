import java.io.FileNotFoundException;

import util.Command;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Duke's main class.
 *
 * Consists of a Storage, TaskList and Ui.
 * Contains the program loop in run() method.
 */
public class Duke {
    // Duke's program variables
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isUpdating;
    private String updateCommand = "";

    /**
     * Duke's Default Constructor.
     * Initialize isUpdating as false.
     * Initializes ui, storage and tasks.
     * Loads in save file (if any).
     * Uses a default filePath
     */
    public Duke() {
        isUpdating = false;
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.loadFileContents());
            ui.setGreetings("... Oh! You're back!\nEr, gimme a sec...");
        } catch (FileNotFoundException e) {
            ui.setGreetings("... Who? Never mind. Er-hmm.\n");
            tasks = new TaskList();
        }
    }

    /**
     * Own function to generate a response to user input.
     *
     * @param input User input.
     */
    public String getResponse(String input) {
        // Read user input
        String userInput = input;
        String output = "";
        // If Duke is getting input for updating a task
        if (isUpdating) {
            // Assert that updateCommand is not empty string
            assert !updateCommand.isEmpty();
            // Updates task
            output = ui.getOutputSymbol() + tasks.updateTask(updateCommand, input);
            // Reset Duke back to default state
            isUpdating = false;
            updateCommand = "";
            // returns the Updated task details
            return output + ui.getLineBreak();
        }
        try {
            // Parse out the command from the user input
            Command cmd = Parser.parse(userInput);
            // Use switch to process the commands
            switch (cmd) {
            case EXIT:
                // Try to Save the data
                try {
                    boolean saveSuccess = storage.saveToFile(tasks.getTasks());
                    assert saveSuccess; // Save should be successful
                } catch (DukeException e) {
                    output = ui.getError(e);
                } finally {
                    // Return farewells
                    return ui.getFarewells() + output;
                }
            case LIST:
                output = ui.getLineBreak() + ui.getList(tasks);
                break;
            case TODO:
                output = ui.getOutputSymbol() + tasks.createTodo(userInput);
                break;
            case DEADLINE:
                output = ui.getOutputSymbol() + tasks.createDeadline(userInput);
                break;
            case EVENT:
                output = ui.getOutputSymbol() + tasks.createEvent(userInput);
                break;
            case INVALID:
                // Duke does not recognize the commands
                throw new DukeException("Sorry, I don't recognize what you just entered...");
            case DONE:
                output = ui.getOutputSymbol() + tasks.markTaskDone(userInput);
                break;
            case DELETE:
                output = ui.getOutputSymbol() + tasks.deleteTask(userInput);
                break;
            case FIND:
                output = ui.getOutputSymbol() + tasks.searchForKeyword(userInput);
                break;
            case UPDATE:
                output = ui.getOutputSymbol() + tasks.fetchTaskToUpdate(userInput);
                // Valid task to update, then set Duke status to isUpdating
                this.isUpdating = true;
                updateCommand = userInput;
                break;
            default:
                assert false : cmd;
                //throw new DukeException("Sorry I think I something went wrong...");
            }
        } catch (DukeException e) {
            output = ui.getLineBreak() + ui.getError(e);
        } finally {
            return output + ui.getLineBreak();
        }
    }

    /**
     * Print's Duke's greetings in GUI.
     */
    public String getGreetings() {
        return ui.getGreetings();
    }
}

package duke.admin;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.utility.MyString;
import duke.utility.Parser;

/**
 * Duke best chatbot hehe.
 * Operate multiple method to handle the input from the user.
 */
public class Duke {
    // File to store data should be directly in folder data, any name is acceptable.
    private final String FILE_PATH = "data/duke.txt";
    private final Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;

    public String getResponse(String input) throws DukeException {
        return handleCommand(input);
    }

    /**
     * Constructor.
     * Load previous tasks from data stored in FILE_PATH.
     */
    public Duke() {
        tasks = new TaskList(storage.readData());
    }

    /**
     * Parse command to tasks to handle.
     * @param cmd User's command
     * @return Duke's response
     */
    private String handleCommand(String cmd) throws DukeException {
        MyString response = new MyString();

        Parser.CommandType commandType = Parser.getType(cmd);
        switch (commandType) {
            // commands handled by TaskList
            case LIST:
                tasks.handleList(response);
                break;
            case DELETE:
                tasks.handleDelete(cmd, response);
                break;
            case FIND:
                tasks.handleFind(cmd, response);
                break;
            case DONE:
                tasks.handleDone(cmd, response);
                break;
            case TODO:
                tasks.handleToDo(cmd, response);
                break;
            case EVENT:
                tasks.handleEvent(cmd, response);
                break;
            case DEADLINE:
                tasks.handleDeadline(cmd, response);
                break;
            case DO_WITHIN:
                tasks.handleDoWithin(cmd, response);
                break;
            // commands handled by UI only
            case BYE:
                throw new DukeException("");
            case INVALID:
                Ui.invalidCommand(response);
                break;
            default:
                break;
        }

        storage.updateDataFile(tasks.getArrayList());

        return response.toString();
    }
}

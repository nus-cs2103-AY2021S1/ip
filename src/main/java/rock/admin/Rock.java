package rock.admin;

import rock.exception.RockException;
import rock.storage.Storage;
import rock.ui.Ui;
import rock.utility.RockResponse;
import rock.utility.Parser;

/**
 * Rock best chatbot hehe.
 * Operate multiple method to handle the input from the user.
 */
public class Rock {
    // File to store data should be directly in folder data, any name is acceptable.
    private static final String FILE_PATH = "data/rock.txt";
    private final Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;

    /**
     * Constructor.
     * Load previous tasks from data stored in FILE_PATH.
     */
    public Rock() {
        tasks = new TaskList(storage.readData());
    }

    public String getResponse(String input) throws RockException {
        return handleCommand(input);
    }

    /**
     * Parse command to tasks to handle.
     * @param cmd User's command
     * @return Duke's response
     */
    private String handleCommand(String cmd) throws RockException {
        cmd = cmd.trim();
        RockResponse response = new RockResponse();

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
            throw new RockException("terminate");
        case INVALID:
            Ui.invalidCommand(response);
            break;
        case SWITCH:
            throw new RockException("switch");
        default:
            break;
        }

        storage.updateDataFile(tasks.getArrayList());

        return response.toString();
    }
}

package duke;

/**
 * Duke best chatbot hehe.
 * Operate multiple method to handle the input from the user.
 */
public class Duke {
    // File to store data should be directly in folder data, any name is acceptable.
    private final String FILE_PATH = "data/duke.txt";
    private final Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;

    public String getResponse(String input) {
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
    private String handleCommand(String cmd) {
        MyString respond = new MyString();

        Parser.CommandType commandType = Parser.getType(cmd);
        switch (commandType) {
            // commands handled by TaskList
            case LIST:
                tasks.handleList(respond);
                break;
            case DELETE:
                tasks.handleDelete(cmd, respond);
                break;
            case FIND:
                tasks.handleFind(cmd, respond);
                break;
            case DONE:
                tasks.handleDone(cmd, respond);
                break;
            case TODO:
                tasks.handleToDo(cmd, respond);
                break;
            case EVENT:
                tasks.handleEvent(cmd, respond);
                break;
            case DEADLINE:
                tasks.handleDeadline(cmd, respond);
                break;
            case DO_WITHIN:
                tasks.handleDoWithin(cmd, respond);
                break;
            // commands handled by UI only
            case BYE:
                Ui.sayBye(respond);
                break;
            case INVALID:
                Ui.invalidCommand(respond);
                break;
            default:
                break;
        }

        storage.updateDataFile(tasks.getArrayList());

        return respond.toString();
    }
}

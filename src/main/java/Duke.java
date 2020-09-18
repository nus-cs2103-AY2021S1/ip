public class Duke {
    /** Object dealing with loading/saving tasks. */
    private final Storage storage;

    /** Object containing the list of tasks. */
    private final TaskList tasks;

    /**
     * Constructs new Duke object.
     *
     * @param filePath Destination path of the duke.txt storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application with the basic logic.
     *
     * @param command User command.
     * @return Duke's response.
     */
    public String run(String command) {
        String response;
        String action = Parser.getAction(command);
        switch (action) {
        case "list":
            response = tasks.printList();
            break;
        case "done":
            response = tasks.markDone(command);
            break;
        case "todo":
            response = tasks.createToDo(command);
            break;
        case "deadline":
            response = tasks.createDeadline(command);
            break;
        case "event":
            response = tasks.createEvent(command);
            break;
        case "delete":
            response = tasks.delete(command);
            break;
        case "find":
            response = tasks.find(command);
            break;
        default:
            response = tasks.getDefaultError();
        }
        storage.save(tasks.getTasks());
        return response;
    }

    /**
     * Retrieves the opening lines of Duke.
     *
     * @return Opening lines when user starts the application.
     */
    public String getOpening() {
        return Ui.opening();
    }

    /**
     * Retrieves the closing lines of Duke.
     *
     * @return Closing lines when user ends the application.
     */
    public String getClosing() {
        return Ui.closing();
    }

    /**
     * Retrieves Duke's response via a wrapper method.
     *
     * @param input User command.
     * @return Duke response.
     */
    public String getResponse(String input) {
        return this.run(input);
    }
}

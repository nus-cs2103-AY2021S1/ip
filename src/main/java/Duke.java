/**
 * Represents the Duke bot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates a Duke bot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, "duke");
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getResponse(String input) {
        return Parser.parse(taskList, storage, input);
    }
}

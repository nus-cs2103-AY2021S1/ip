/**
 * Represents the Duke bot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a Duke bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, "duke");
    }

//    /**
//     * Runs the Duke bot program
//     */
//    public void run() {
//        ui.startUp(taskList, storage);
//        Parser.parseInput(taskList, storage);
//    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getResponse(String input) {
        if (input.equals("start")) {
            return ui.startUp(taskList, storage);
        } else {
            return Parser.parse(taskList, storage, input);
        }
    }
}

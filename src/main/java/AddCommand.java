/**
 * Represents a command by the user to add a task into the list of tasks.
 * An AddCommand object has a task type, which is either a todo, deadline or event task,
 * and a string array with the task description separated into the task name and date/time.
 */
public class AddCommand extends Command {
    public static int TODO = 1;
    public static int DEADLINE = 2;
    public static int EVENT = 3;

    private int taskType;
    private String[] taskInfos;

    /**
     * Instantiates an AddCommand object.
     * @param taskType The type of task to be added.
     * @param taskInfos The description of the task to be added.
     */
    public AddCommand(int taskType, String[] taskInfos) {
        this.taskType = taskType;
        this.taskInfos = taskInfos;
    }

    /**
     * Executes the command by adding the new task to the list, replying the user,
     * and updating the list of tasks stored in the computer.
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If the deadline or event is not specified in the correct format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskType == TODO) {
            Task t = new Todo(this.taskInfos[0]);
            tasks.addTask(t);
            ui.showAdd(t);
        } else if (this.taskType == DEADLINE) {
            Task t = new Deadline(this.taskInfos[0], this.taskInfos[1]);
            tasks.addTask(t);
            ui.showAdd(t);
        } else {
            Task t = new Event(this.taskInfos[0], this.taskInfos[1]);
            tasks.addTask(t);
            ui.showAdd(t);
        }

        storage.updateData(tasks);
    }

    /**
     * Returns false as the command is not an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

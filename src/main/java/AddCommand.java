public class AddCommand extends Command {
    public static int TODO = 1;
    public static int DEADLINE = 2;
    public static int EVENT = 3;
    private int taskType;
    private String[] taskDesc;

    public AddCommand(int taskType, String[] taskDesc) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t;
        if (this.taskType == TODO) {
            t = new Todo(this.taskDesc[0]);
        } else if (this.taskType == DEADLINE) {
            t = new Deadline(this.taskDesc[0], this.taskDesc[1]);
        } else {
            t = new Event(this.taskDesc[0], this.taskDesc[1]);
        }
        tasks.addTask(t);
        ui.showAdd(t);
        storage.updateData(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

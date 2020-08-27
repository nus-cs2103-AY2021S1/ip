public class AddCommand extends Command {
    public static int TODO = 1;
    public static int DEADLINE = 2;
    public static int EVENT = 3;

    private int taskType;
    private String[] taskInfos;

    public AddCommand(int taskType, String[] taskInfos) {
        this.taskType = taskType;
        this.taskInfos = taskInfos;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}

public class AddCommand extends Command {
    private final DukeTask task;

    public AddCommand(DukeTask task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {
        taskList.addToList(task);
        uiManager.printAddTask(task, taskList.getSize());
    }
}

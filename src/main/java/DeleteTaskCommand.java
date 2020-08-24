public class DeleteTaskCommand implements Command {
    Storage store;
    int taskIndex;

    public DeleteTaskCommand(Storage store, int taskIndex) {
        this.store = store;
        this.taskIndex = taskIndex;
    }

    public void execute() {
        store.deleteTask(taskIndex);
    }
}

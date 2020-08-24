public class CompleteTaskCommand implements Command {
    Storage store;
    int taskIndex;
    public CompleteTaskCommand(Storage store, int taskIndex) {
        this.store = store;
        this.taskIndex = taskIndex;
    }

    public void execute() {
        store.completeTask(taskIndex);
    }
}

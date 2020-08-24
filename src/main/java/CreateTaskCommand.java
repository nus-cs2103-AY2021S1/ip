public class CreateTaskCommand implements Command {
    Storage store;
    Task task;
    public CreateTaskCommand(Storage store, Task task) {
        this.store = store;
        this.task = task;
    }

    public void execute() {
        store.addTask(task);
    }
}

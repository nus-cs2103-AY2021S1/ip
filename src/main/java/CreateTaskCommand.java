public class CreateTaskCommand implements Command {
    Storage store;
    Task task;
    public CreateTaskCommand(Storage store, Task task) {
        this.store = store;
        this.task = task;
    }

    @Override
    public void execute() {
        String message = String.format("Added: %s", task.toString());
        System.out.println(message);
        store.addTask(task);
    }
}

public class TodoCommand extends Command {
    Task toDoTask;

    public TodoCommand(String description) {
        toDoTask = new Todo(description);
    }

    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(toDoTask);
        arrayOfTasks.addTask(toDoTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    public boolean exitCheck() {
        return false;
    }
}
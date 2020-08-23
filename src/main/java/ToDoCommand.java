package main.java;

public class ToDoCommand extends Command {
    private String taskName;

    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDoTask(taskName);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}

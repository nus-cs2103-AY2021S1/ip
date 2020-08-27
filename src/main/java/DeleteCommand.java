package main.java;

public class DeleteCommand extends Command {
    int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(id);
        ui.taskDeleted(task, tasks);
    }
}

package main.java;

public class DoneCommand extends Command {
    int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.setDone(id);
        ui.taskDone(task);
    }
}

package main.java;

public class AddCommand extends Command {
    String data;

    public AddCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task task = Task.fromData(data);
        tasks.add(task);
        ui.taskAdded(task, tasks);
    }
}

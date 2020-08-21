package main.java;

public class TaskListCommand extends Command {

    public TaskListCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}

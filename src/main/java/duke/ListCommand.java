package duke;

public class ListCommand implements Command {
    TaskList tasks;
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        tasks.list();
    }
}

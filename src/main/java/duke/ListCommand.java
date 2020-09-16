package duke;

public class ListCommand implements Command {
    TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return tasks.list();
    }
}

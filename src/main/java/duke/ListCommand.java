package duke;

public class ListCommand extends PrintTaskCommand {
    public ListCommand(TaskList tasklist) {
        super(Command.LIST, tasklist);
    }

    public String execute() {
        return outputTasksInTaskList(this.taskList, false);
    }
}

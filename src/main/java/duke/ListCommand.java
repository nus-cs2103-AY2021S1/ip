package duke;

public class ListCommand extends PrintTaskCommand {
    public ListCommand(TaskList tasklist) {
        super(Command.LIST, tasklist);
    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @return Description of all the Tasks in TaskList.
     */
    public String execute() {
        return outputTasksInTaskList(this.taskList, false);
    }
}

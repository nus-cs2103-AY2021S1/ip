package command;

import task.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Lists tasks in list of tasks.
     * @return String that lists all tasks.
     */
    @Override
    public String execute() {
        return this.tasks.list();
    }
}


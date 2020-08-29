package command;

import task.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Lists tasks in list of tasks.
     */
    @Override
    public void execute() {
        this.tasks.list();
    }
}


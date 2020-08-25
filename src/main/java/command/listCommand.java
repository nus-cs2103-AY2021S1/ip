package command;

import exceptions.DukeException;
import task.TaskList;

public class listCommand extends Command {

    public listCommand(TaskList tasks) {
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

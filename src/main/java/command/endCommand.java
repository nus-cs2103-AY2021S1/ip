package command;

import exceptions.DukeException;
import task.TaskList;

public class endCommand extends Command {

    public endCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Updates isExit boolean to true.
     */
    @Override
    public void execute() {
        this.isExit = true;
    }
}

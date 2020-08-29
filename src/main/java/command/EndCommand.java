package command;

import task.TaskList;

public class EndCommand extends Command {

    public EndCommand(TaskList tasks) {
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

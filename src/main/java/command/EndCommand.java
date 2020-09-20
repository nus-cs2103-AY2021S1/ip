package command;

import task.TaskList;

public class EndCommand extends Command {

    public EndCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Updates isExit boolean to true.
     * @return String "exit" to signal termination of program.
     */
    @Override
    public String execute() {
        return "exit";
    }
}

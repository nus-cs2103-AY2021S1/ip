package duke.command;

import duke.TaskList;

public class ErrorCommand implements Command {
    private final String errMsg;

    public ErrorCommand(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String execute(TaskList tasks) {
        return errMsg;
    }
}

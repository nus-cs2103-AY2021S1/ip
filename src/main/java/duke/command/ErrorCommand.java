package duke.command;

import duke.exception.DukeException;

public class ErrorCommand extends Command {
    private String errMsg;

    public ErrorCommand(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String execute() throws DukeException {
        throw new DukeException(errMsg);
    }
}

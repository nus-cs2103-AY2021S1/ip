package duke.commands;

import duke.exceptions.DukeException;

import duke.task.TaskManager;

public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        throw new DukeException(errorMessage);
    }
}

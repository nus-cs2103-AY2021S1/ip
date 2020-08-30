package duke.command;

import java.util.List;

import duke.exception.UserException;
import duke.task.TaskList;


public abstract class Command {
    protected List<String> input;

    /**
     * Creates a new command with user's input.
     *
     * @param input
     */
    public Command(List<String> input) {
        this.input = input;
    }

    /**
     * Runs the command.
     *
     * @param taskList the current list of tasks
     * @throws UserException
     */
    public abstract String run(TaskList taskList) throws UserException;
}


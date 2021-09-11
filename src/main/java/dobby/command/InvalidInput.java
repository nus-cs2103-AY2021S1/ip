package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class InvalidInput implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        throw new DobbyException("Sorry that command is not supported. Please try again.");
    }
}

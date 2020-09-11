package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class ByeCommand implements Command {

    protected static final String USAGE = "bye";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        assert text.equals("bye") : "Bye command must be bye";
        return "Goodbye.\nHope to see you again soon!";
    }
}

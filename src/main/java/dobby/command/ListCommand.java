package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class ListCommand implements Command {

    protected static final String USAGE = "list";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        assert text.equals("list") : "Input text must be list";
        return tasks.getListedTasks();
    }

}

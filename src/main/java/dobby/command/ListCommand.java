package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class ListCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        assert text.equals("list") : "Input text must be list";
        return tasks.getListedTasks();
    }

}

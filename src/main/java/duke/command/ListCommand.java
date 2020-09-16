package duke.command;

import duke.main.Statement;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.Parser;

public class ListCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.LIST + "]";

    @Override
    public Response process() {
        TaskList<Task> taskList = Parser.getTaskList();
        return new Response(Statement.LIST.toString() + taskList);
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.main.Statement;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.Parser;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {
    @Override
    public Response process() throws DukeException {
        TaskList<Task> taskList = Parser.getTaskList();
        return new Response(Statement.LIST.toString() + taskList);
    }
}

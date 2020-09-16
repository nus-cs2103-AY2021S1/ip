package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Task;
import duke.task.Todo;
import duke.tools.Format;

/**
 * Represents a todo command.
 */
public class TodoCommand implements Command {

    private final String detail;

    /**
     * Construct a todo command.
     *
     * @param detail the detail of the todo task.
     */
    public TodoCommand(String detail) {
        this.detail = detail;
    }

    @Override
    public Response process() throws DukeException {
        Task task = new Todo(detail);
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(
                new Format<>(task).toString()
        );
    }
}

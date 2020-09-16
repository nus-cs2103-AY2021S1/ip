package duke.command;

import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.TODO + "] <detail>";

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
    public Response process() {
        Task task = new Todo(detail);
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(
                task.toString()
        );
    }
}

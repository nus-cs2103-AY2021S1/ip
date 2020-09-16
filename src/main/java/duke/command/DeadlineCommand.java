package duke.command;

import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.tools.Time;

import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.DEADLINE
            + "] <detail> /by <when>";

    private final String detail;
    private final Time by;

    /**
     * Constructs a deadline command.
     *
     * @param detail the detail of the deadline command.
     * @param by the time of the deadline command.
     */
    public DeadlineCommand(String detail, Time by) {
        this.detail = detail;
        this.by = by;
    }

    @Override
    public Response process() {
        Task task = new Deadline(detail, by.toString());
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(task.toString());
    }
}

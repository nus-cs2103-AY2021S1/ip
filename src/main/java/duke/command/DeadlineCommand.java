package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Deadline;
import duke.task.Task;
import duke.tools.Format;
import duke.tools.Time;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand implements Command {
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
    public Response process() throws DukeException {
        Task task = new Deadline(detail, by.toString());
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(
                new Format<>(task).toString()
        );
    }
}

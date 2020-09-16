package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Event;
import duke.task.Task;
import duke.tools.Format;
import duke.tools.Time;

/**
 * Represents a event command.
 */
public class EventCommand implements Command {
    private final String detail;
    private final Time on;

    /**
     * Constructs an event command.
     * @param detail the detail of the event command.
     * @param on the time of the deadline command.
     */
    public EventCommand(String detail, Time on) {
        this.detail = detail;
        this.on = on;
    }

    @Override
    public Response process() throws DukeException {
        Task task = new Event(detail, on.toString());
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(
                new Format<>(task).toString()
        );
    }
}

package duke.command;

import duke.main.Directory;
import duke.storage.DukeFileWriter;
import duke.task.Event;
import duke.task.Task;
import duke.tools.Time;

public class EventCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.EVENT
            + "] <detail> /on <when>";

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
    public Response process() {
        Task task = new Event(detail, on.toString());
        DukeFileWriter data = new DukeFileWriter(Directory.FILEDIRECTORY, true);
        data.writeToFile(task.toString());
        return new Response(task.toString());
    }
}

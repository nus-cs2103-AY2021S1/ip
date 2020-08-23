package duke.command;

import duke.*;
import duke.exceptions.DukeException;
import duke.parser.DateParser;
import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private final String description;
    private final String dateStr;

    public EventCommand(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime eventDateTime = DateParser.parseString(dateStr);
        Event event = new Event(description, eventDateTime);
        tasks.addTask(event);
        ui.printMessage(String.format("Okay, I've added the following event: \n %s", event.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof EventCommand) {
            return this.description.equals(((EventCommand) other).description) &&
                    this.dateStr.equals(((EventCommand) other).dateStr);
        } else {
            return false;
        }
    }
}

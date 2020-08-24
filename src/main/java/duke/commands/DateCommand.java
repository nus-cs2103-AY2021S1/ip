package duke.commands;

import duke.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DateCommand extends Command {
    private String description;

    public DateCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean dateExists = false;
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if(((Deadline) i).hasDate(description)) {
                    ui.showMessage(i.toString());
                    dateExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).hasDate(description)) {
                    ui.showMessage(i.toString());
                    dateExists = true;
                }
            }
        }
        if (!dateExists) {
            throw new DukeException("No events/deadlines with this date!");
        }
    }
}

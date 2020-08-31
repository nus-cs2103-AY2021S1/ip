package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.TaskType;
import duke.exception.PastDateTimeException;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String task;
    private LocalDateTime dateTime;

    public EventCommand(String task, LocalDateTime dateTime) {
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds an Event task with task and dateTime to the TaskList.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws PastDateTimeException If the dateTime has already passed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PastDateTimeException {
        if (LocalDateTime.now().isBefore(dateTime)) {
            tasks.add(task, dateTime, TaskType.EVENT);
            ui.say("Added Event '" + task + "' to your list!");
        } else {
            throw (new PastDateTimeException());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventCommand;
    }
}

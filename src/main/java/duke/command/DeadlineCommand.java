package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.task.TaskType;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.PastDateTimeException;

public class DeadlineCommand extends Command {
    private String task;
    private LocalDateTime dateTime;

    /**
     * Creates a DeadlineCommand with the given description and date.
     *
     * @param task The given description.
     * @param dateTime The given date.
     */
    public DeadlineCommand(String task, LocalDateTime dateTime) {
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds a Deadline task with task and dateTime to the TaskList.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @returns Duke response.
     * @throws PastDateTimeException If the dateTime has already passed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (LocalDateTime.now().isBefore(dateTime)) {
            tasks.add(task, dateTime, TaskType.DEADLINE);
            return ("Added Deadline '" + task + "' to your list!");
        } else {
            throw (new PastDateTimeException());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}

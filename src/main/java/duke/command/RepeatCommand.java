package duke.command;

import duke.RecurrenceType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDateTime;

public class RepeatCommand extends Command {
    private int index;
    private LocalDateTime end;
    private long recurrence;
    private RecurrenceType recurType;

    public RepeatCommand(int index, LocalDateTime end, long recurrence,
                         RecurrenceType recurType) {
        this.index = index;
        this.end = end;
        this.recurrence = recurrence;
        this.recurType = recurType;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.repeatTask(index, end, recurrence, recurType);
        return String.format("%s has been added and set to repeat.", task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RepeatCommand;
    }
}

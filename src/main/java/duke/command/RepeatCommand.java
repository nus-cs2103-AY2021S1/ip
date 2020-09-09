package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDateTime;

public class RepeatCommand extends Command {
    private int index;
    private LocalDateTime end;
    private long recurrence;

    public RepeatCommand(int index, LocalDateTime end, long recurrence) {
        this.index = index;
        this.end = end;
        this.recurrence = recurrence;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.repeatTask(index,end, recurrence);
        return String.format("%s has been added and set to repeat.", task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RepeatCommand;
    }
}

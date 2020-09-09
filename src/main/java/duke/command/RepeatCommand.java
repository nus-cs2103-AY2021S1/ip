package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDateTime;

public class RepeatCommand extends Command {
    private String desc;
    private LocalDateTime start;
    private TaskType type;
    private LocalDateTime end;
    private long recurrence;

    public RepeatCommand(String desc, LocalDateTime start, TaskType type,
                         LocalDateTime end, long recurrence) {
        this.desc = desc;
        this.start = start;
        this.type = type;
        this.end = end;
        this.recurrence = recurrence;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime date = start;
        while (date.isBefore(end) || date.isEqual(end)) {
            tasks.add(desc, date, type);
            date = date.plusDays(recurrence);
        }
        return String.format("Task '%s' has been added and set to repeat.", desc);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RepeatCommand;
    }
}

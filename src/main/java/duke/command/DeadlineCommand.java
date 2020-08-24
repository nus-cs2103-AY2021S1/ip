package duke.command;

import java.time.LocalDateTime;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.TaskType;

public class DeadlineCommand extends Command {
    private String task;
    private LocalDateTime dateTime;

    public DeadlineCommand(String task, LocalDateTime dateTime) {
        this.task = task;
        this.dateTime = dateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (LocalDateTime.now().isBefore(dateTime)) {
            tasks.add(task, dateTime, TaskType.DEADLINE);
            ui.say("Added Deadline '" + task + "' to your list!");
        } else {
            throw(DukeException.pastDateTime());
        }
    }
}

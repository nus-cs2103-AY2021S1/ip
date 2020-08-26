package duke.command;

import duke.task.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;
    
    public DeadlineCommand(String description, LocalDateTime by) {
        super();
        this.description = description;
        this.by = by;
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(new Deadline(description, by));
        storage.saveTasks(taskList.getTask());
    }
}

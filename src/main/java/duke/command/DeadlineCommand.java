package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends TaskCreationCommand {
    public final static String COMMAND = "deadline";
    public final static String TIME_SPECIFIER = "/by";
    
    private String description;
    private String time;
    
    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, time);
        super.execute(task, ui, tasks);
    }
}

package duke.commands;

import java.time.LocalDate;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.UI;



public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String deadlineDate;
    private LocalDate deadlineLocalDate;
    private boolean hasLocalDate;

    public DeadlineCommand(String commandDescription, String deadlineDate) {
        super(commandDescription, false);
        this.deadlineDate = deadlineDate;
        this.hasLocalDate = false;
    }

    public DeadlineCommand(String commandDescription, LocalDate deadlineLocalDate) {
        super(commandDescription, false);
        this.deadlineLocalDate = deadlineLocalDate;
        this.hasLocalDate = true;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Deadline deadlineTask;
        if (this.hasLocalDate) {
            deadlineTask = new Deadline(commandDescription, deadlineLocalDate);
        } else {
            deadlineTask = new Deadline(commandDescription, deadlineDate);
        }
        taskList.addToList(deadlineTask);
        ui.displayAddedTask(deadlineTask, taskList.getListSize());
    }
}

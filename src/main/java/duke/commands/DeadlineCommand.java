package duke.commands;

import java.time.LocalDate;

import duke.tasks.Deadline;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents a Deadline Command when the user inputs
 * a new task with a deadline.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String deadlineDate;
    private LocalDate deadlineLocalDate;
    private boolean hasLocalDate;

    /**
     * Creates a new instance of a Deadline Command with the appropriate
     * deadline description and date of deadline given in non standard format.
     *
     * @param commandDescription Description of the command body.
     * @param deadlineDate Date of deadline.
     */
    public DeadlineCommand(String commandDescription, String deadlineDate) {
        super(commandDescription, false);
        this.deadlineDate = deadlineDate;
        this.hasLocalDate = false;
    }

    /**
     * Creates a new instance of a Deadline Command with the appropriate
     * command description and date of deadline given in standard format.
     *
     * @param commandDescription Description of the command body.
     * @param deadlineLocalDate Date of deadline as a LocalDate object.
     */
    public DeadlineCommand(String commandDescription, LocalDate deadlineLocalDate) {
        super(commandDescription, false);
        this.deadlineLocalDate = deadlineLocalDate;
        this.hasLocalDate = true;
    }

    /**
     * Executes the command.
     * Creates a new Deadline object and adds it to the list
     * of taks.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
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

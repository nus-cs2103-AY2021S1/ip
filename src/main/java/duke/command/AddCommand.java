package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskType;

import java.time.LocalDateTime;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String description;
    private final LocalDateTime dateTime;

    public AddCommand(TaskType type, String description) {
        this(type, description, null);
    }

    public AddCommand(TaskType type, String description, LocalDateTime dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes the command, adding a task to the given TaskList.
     *
     * @param taskList A TaskList instance.
     * @param ui A Ui instance.
     * @param storage A Storage instance.
     * @throws DukeException if the task cannot be added.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.addTask(this.type, this.description, this.dateTime);
        ui.showPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + taskList.getTasks().size()
                + (taskList.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return this.type.equals(((AddCommand) obj).type)
                    && this.description.equals(((AddCommand) obj).description)
                    && (this.dateTime == null || this.dateTime.equals(((AddCommand) obj).dateTime));
        }

        return false;
    }
}

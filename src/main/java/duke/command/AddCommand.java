package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Class representing an add task command.
 */
public class AddCommand extends Command {

    private final CommandType taskType;
    private final String[] taskContent;

    /**
     * Creates a new {@code AddCommand}.
     * @param taskType Type of the task to be created.
     * @param taskContent Contents of the task.
     */
    public AddCommand(CommandType taskType, String[] taskContent) {
        this.taskType = taskType;
        this.taskContent = taskContent;
    }

    /**
     * Executes the command and creates the respective tasks.
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     * @throws DukeException If the tasks fails to create.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;

        switch (taskType) {
        case TODO: {
            task = new ToDo(taskContent[0]);
            break;
        }
        case DEADLINE: {
            task = new Deadline(taskContent[0].trim(), taskContent[1].trim());
            break;
        }
        case EVENT: {
            task = new Event(taskContent[0].trim(), taskContent[1].trim());
            break;
        }
        default:
            throw new DukeException("Failed to create event.");
        }
        tasks.addTask(task);
        storage.saveData(tasks);

        StringBuilder message = new StringBuilder("Alright! I've added this task:\n");
        message.append(task);
        message.append("\nNow you have ").append(tasks.size()).append(" task(s) in your list.");
        ui.botOutput(message);
    }
}

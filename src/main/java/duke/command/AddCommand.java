package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The {@code CompleteCommand} class represents a command to create a new {@link Task}.
 */
public class AddCommand extends Command {

    private Task createdTask;
    private int remainingTaskCount;

    /**
     * Constructs an {@code AddCommand} with the specified type and specified task details.
     *
     * @param type           the type of {@code Task} to be added.
     * @param taskDetail     the details of the task to be added.
     * @throws DukeException if the specified type is null.
     */
    public AddCommand(TaskType type, String taskDetail) throws DukeException {
        switch (type) {
        case DEADLINE:
            createdTask = Deadline.createTask(taskDetail);
            break;
        case EVENT:
            createdTask = Event.createTask(taskDetail);
            break;
        case TODO:
            createdTask = ToDo.createTask(taskDetail);
            break;
        default:
            throw new DukeException("I don't understand.");
        }
    }

    /**
     * Executes this {@code AddCommand} by adding the created {@code Task} to the specified {@code list}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        list.add(createdTask);
        remainingTaskCount = list.taskCount();
        storage.save(list);
        super.completed = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code AddCommand}.
     *
     * @param ui the {@link Ui} instance to use for formatting.
     * @throws IncompleteDukeCommandException if this {@code AddCommand} was not executed.
     */
    @Override
    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = String.format(
                    "Got it. I've added this task:\n  %s\nNow you have %d tasks in your list.\n",
                    createdTask.toString(),
                    remainingTaskCount);
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Add command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

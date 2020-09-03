package nekochan.command;

import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.exceptions.NekoException;
import nekochan.storage.Storage;
import nekochan.task.Deadline;
import nekochan.task.Event;
import nekochan.task.Task;
import nekochan.task.TaskList;
import nekochan.task.TaskType;
import nekochan.task.ToDo;

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
     * @throws NekoException if the specified type is null.
     */
    public AddCommand(TaskType type, String taskDetail) throws NekoException {
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
            throw new NekoException("I don't understand.");
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
        remainingTaskCount = list.getTaskCount();
        storage.save(list);
        super.isCompleted = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code AddCommand}.
     *
     * @throws IncompleteNekoCommandException if this {@code AddCommand} was not executed.
     */
    @Override
    public String feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException("Add command was not completed.");
        }
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in your list.\n",
                createdTask.toString(),
                remainingTaskCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

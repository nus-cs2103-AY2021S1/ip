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
import nekochan.util.Messages;

/**
 * The {@code CompleteCommand} class represents a command to create a new {@link Task}.
 */
public class AddCommand extends Command {

    private static final boolean IS_EXIT = false;

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
            throw new NekoException(Messages.INVALID_TASK_TYPE_ERROR);
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
     * Returns a {@link Response} from the execution of this {@code AddCommand}.
     *
     * @return a {@code Response} object containing the result of executing this {@code AddCommand}.
     * @throws IncompleteNekoCommandException if this {@code AddCommand} was not executed.
     */
    @Override
    public Response feedback() throws IncompleteNekoCommandException {
        if (!super.isCompleted) {
            throw new IncompleteNekoCommandException(Messages.INCOMPLETE_ADD_COMMAND);
        }

        assert createdTask != null : "created task should not be null";
      
        String responseMessage = Messages.MESSAGE_ADD + createdTask.toString() + "\n"
                + Messages.getTotalTaskMessage(remainingTaskCount);
        return new Response(IS_EXIT, responseMessage);
    }
}

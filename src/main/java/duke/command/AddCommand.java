package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.Task;
import duke.task.TaskException;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Encapsulates the logic for adding tasks.
 */
public class AddCommand extends Command {
    private final TaskType taskType;

    /**
     * Constructs a AddCommand with a given TaskType.
     *
     * @param args Arguments for the command.
     * @param taskType TaskType for which task to add.
     */
    public AddCommand(String args, TaskType taskType) {
        super(args);
        this.taskType = taskType;
    }

    /**
     * Dispatches the appropriate method to add a certain task type into the given taskList.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return A message to indicate results.
     * @throws TaskException If exception happened while processing task.
     * @throws StorageException If exception happened while storing.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        Task newTask;
        switch (taskType) {
        case DEADLINE:
            newTask = taskList.addDeadline(args);
            break;
        case EVENT:
            newTask = taskList.addEvent(args);
            break;
        case TODO:
            newTask = taskList.addTodo(args);
            break;
        default:
            throw new TaskException("Oops. I didn't recognise this task.");
        }
        assert newTask != null : "Task has not been created";
        storage.save(taskList);
        return "Alright! Adding one more item:\n" + newTask.toString();
    }
}

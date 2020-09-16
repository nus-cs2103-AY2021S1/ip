package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.exception.DuplicateTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to add a Task.
 */
public class AddTaskCommand extends Command {
    private TaskType type;
    private String taskName;
    private LocalDate taskDate;

    /**
     * Creates a AddTaskCommand.
     * @param type A TaskType object representing the type of Task to be created.
     * @param name A String representing the Task name.
     */
    public AddTaskCommand(TaskType type, String name) {
        this.type = type;
        this.taskName = name;
    }

    /**
     * Creates a AddTaskCommand.
     * @param type A TaskType object representing the type of Task to be created.
     * @param name A String representing the Task name.
     * @param date A LocalDate representing the Task date.
     */
    public AddTaskCommand(TaskType type, String name, LocalDate date) {
        this.type = type;
        this.taskName = name;
        this.taskDate = date;
    }

    /**
     * Executes the AddTaskCommand.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String containing a user message, stating the task that was successfully added.
     */
    @Override
    public String execute(TaskList list, Storage storage) throws DuplicateTaskException {
        switch (this.type) {
        case TODO:
            Task newTodo = list.addTask(this.taskName);
            storage.appendTaskStorage(newTodo.toSaveString());
            return DukeMessages.printAddTaskMessage(newTodo, list.getTaskListSize());
        case EVENT:
        case DEADLINE:
            Task newTask = list.addTask(this.type, this.taskName, this.taskDate);
            storage.appendTaskStorage(newTask.toSaveString());
            return DukeMessages.printAddTaskMessage(newTask, list.getTaskListSize());
        default:
            throw new AssertionError("Invalid TaskType");
        }
    }
}

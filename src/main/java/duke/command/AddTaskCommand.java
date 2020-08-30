package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDate;

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
    public AddTaskCommand(TaskType type, String name){
        this.type = type;
        this.taskName = name;
    }

    /**
     * Creates a AddTaskCommand.
     * @param type A TaskType object representing the type of Task to be created.
     * @param name A String representing the Task name.
     * @param date A LocalDate representing the Task date.
     */
    public AddTaskCommand(TaskType type, String name, LocalDate date){
        this.type = type;
        this.taskName = name;
        this.taskDate = date;
    }

    /**
     * Executes the AddTaskCommand.
     * The Task is first created and added the TaskList, before being stored in local storage.
     * Then, a message is printed to indicate the Task was succesfully created
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @throws StorageException if Task cannot be stored in local storage.
     * @throws InvalidTaskException if details provided of Task to be created are invalid.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws StorageException, InvalidTaskException {
        switch(this.type){
        case TODO:
            Task newTodo = list.addTask(this.taskName);
            storage.appendTaskStorage(newTodo.toSaveString());
            Ui.addTaskMessage(newTodo, list.taskListSize());
            break;
        case EVENT:
        case DEADLINE:
            Task newTask = list.addTask(this.type, this.taskName, this.taskDate);
            storage.appendTaskStorage(newTask.toSaveString());
            Ui.addTaskMessage(newTask, list.taskListSize());
            break;
        }
    }
}

package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.tasks.Task;
import duke.tasks.TaskType;

/**
 * Command sub-type to define adding Tasks.
 */
public class AddCommand extends Command {

    private final TaskType taskType;

    /**
     * Create AddCommand object.
     *
     * @param attributes input attributes from user
     * @param taskType TaskType of the Task to add
     */
    public AddCommand(String attributes, TaskType taskType) {
        this.attributes = attributes;
        this.taskType = taskType;
    }

    /**
     * CLI (terminal) version of the command.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true, continue program loop
     * @throws DukeException Duke-related exception while adding a task to the list
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        runGUI(taskList, storage, ui);
        return true;
    }

    /**
     * Add the task to the list, depending on its type: Todo, Deadline or Event.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     * @throws DukeException Duke-related exception while adding a task to the list
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask;
        if (taskType == TaskType.TODO) {
            newTask = taskList.addTodo(attributes);
        } else if (taskType == TaskType.DEADLINE) {
            newTask = taskList.addTimedTask(attributes, false);
        } else {
            newTask = taskList.addTimedTask(attributes, true);
        }
        storage.storeList(taskList.getList());
        return ui.writeAdd(newTask, taskList.getSize());
    }
}

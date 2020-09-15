package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.tasks.Task;
import duke.tasks.TaskType;

/**
 * Command sub-type to define adding Tasks
 */
public class AddCommand extends Command {

    private final TaskType taskType;

    /**
     * Creates AddCommand object.
     *
     * @param attributes input attributes from user
     * @param taskType TaskType of the Task to add
     */
    public AddCommand(String attributes, TaskType taskType) {
        this.attributes = attributes;
        this.taskType = taskType;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask;
        if (taskType == TaskType.TODO) {
            newTask = taskList.addTodo(attributes);
        } else if (taskType == TaskType.DEADLINE) {
            newTask = taskList.addDDLTask(attributes, false);
        } else {
            newTask = taskList.addDDLTask(attributes, true);
        }
        ui.writeAdd(newTask, taskList.getSize());
        storage.storeList(taskList.getList());
        return true;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask;
        if (taskType == TaskType.TODO) {
            newTask = taskList.addTodo(attributes);
        } else if (taskType == TaskType.DEADLINE) {
            newTask = taskList.addDDLTask(attributes, false);
        } else {
            newTask = taskList.addDDLTask(attributes, true);
        }
        storage.storeList(taskList.getList());
        return ui.writeAdd(newTask, taskList.getSize());
    }
}

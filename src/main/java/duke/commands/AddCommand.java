package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskType;

public class AddCommand extends Command {

    private final TaskType taskType;


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
}

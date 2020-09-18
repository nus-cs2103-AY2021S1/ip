package olivia.command;

import java.util.List;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Task;
import olivia.task.ToDo;
import olivia.ui.Ui;

/**
 * ToDoCommand class that represents a Deadline task being added to the
 * TaskList.
 */

public class ToDoCommand implements Command {

    /**
     * Creates and stores a ToDo object in the TaskList if the input is valid.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command.
     * @return output String to the user.
     */

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        Storage storage = wrapper.getStorage();
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        StringBuilder description = new StringBuilder();
        if (input.size() == 0) {
            return "The description of a todo cannot be empty!";
        }
        for (String s : input) {
            description.append(s).append(" ");
        }
        description.deleteCharAt(description.length() - 1);
        Task task = new ToDo(description.toString(), false);
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAdd(tasks, task);
    }

}

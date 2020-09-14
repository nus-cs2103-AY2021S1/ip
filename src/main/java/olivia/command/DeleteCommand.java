package olivia.command;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Task;
import olivia.ui.Ui;

import java.util.List;

public class DeleteCommand implements Command {

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        int index;
        Storage storage = wrapper.getStorage();
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        if (input.size() == 0) {
            return "Please select a task to mark as completed!";
        }
        try {
            index = Integer.parseInt(input.get(0));
        } catch (NumberFormatException e) {
            return "Please choose an integer value!";
        }
        if (index <= 0) {
            return "Please choose an integer greater than 0!";
        } else if (index > tasks.size()) {
            return "Your task list is not that long yet!";
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save(tasks);
        return ui.showDelete(tasks, task);
    }

}

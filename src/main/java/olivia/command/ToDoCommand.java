package olivia.command;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Task;
import olivia.task.ToDo;
import olivia.ui.Ui;

import java.util.List;

public class ToDoCommand implements Command {

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

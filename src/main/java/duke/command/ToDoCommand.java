package duke.command;

import duke.logic.Storage;
import duke.resource.TaskList;
import duke.resource.Wrapper;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.List;

public class ToDoCommand implements Commands {

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

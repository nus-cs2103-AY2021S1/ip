package duke.command;

import duke.logic.Storage;
import duke.resource.TaskList;
import duke.resource.Wrapper;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.List;

public class UpdateCommand implements Command {

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        Storage storage = wrapper.getStorage();
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        int index;
        if (input.size() == 0) {
            return "Please select a task to update!";
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
        StringBuilder updatedText = new StringBuilder();
        Task task = tasks.getTask(index);
        input.remove(0);
        if (input.size() == 0) {
            return "You can't update a task with an empty description!";
        } else if (task instanceof ToDo) {
            for (String s : input) {
                updatedText.append(s).append(" ");
            }
            updatedText.deleteCharAt(updatedText.length() - 1);
            task.update(updatedText.toString());
        } else if (task instanceof Deadline) {
            String specifier = input.remove(0);
            if (specifier.equals("/description") || specifier.equals("/time")) {
                for (String s : input) {
                    updatedText.append(s).append(" ");
                }
                updatedText.deleteCharAt(updatedText.length() - 1);
                ((Deadline) task).update(updatedText.toString(), specifier);
            }
        } else if (task instanceof Event) {
            String specifier = input.remove(0);
            if (specifier.equals("/description") || specifier.equals("/time")) {
                for (String s : input) {
                    updatedText.append(s).append(" ");
                }
                updatedText.deleteCharAt(updatedText.length() - 1);
                ((Event) task).update(updatedText.toString(), specifier);
            }
        }
        storage.save(tasks);
        return ui.showUpdate(task);
    }

}

package olivia.command;

import java.util.List;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Deadline;
import olivia.task.Event;
import olivia.task.Task;
import olivia.task.ToDo;
import olivia.ui.Ui;

/**
 * UpdateCommand class that represents one of a task's fields being updated.
 */

public class UpdateCommand implements Command {

    /**
     * Updates one of the fields of the task indicated by the given index
     * in the TaskList.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command.
     * @return output String to the user.
     */

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
                Deadline deadline = (Deadline) task;
                deadline.update(updatedText.toString(), specifier);
            }
        } else if (task instanceof Event) {
            String specifier = input.remove(0);
            if (specifier.equals("/description") || specifier.equals("/time")) {
                for (String s : input) {
                    updatedText.append(s).append(" ");
                }
                updatedText.deleteCharAt(updatedText.length() - 1);
                Event event = (Event) task;
                event.update(updatedText.toString(), specifier);
            }
        }
        storage.save(tasks);
        return ui.showUpdate(task);
    }

}

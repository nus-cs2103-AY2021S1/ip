package olivia.command;

import java.util.List;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.ui.Ui;

/**
 * DoneCommand class that represents a task being marked as completed.
 */

public class DoneCommand implements Command {

    /**
     * Marks a task indicated by the given index in the TaskList as done.
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
        tasks.completeTask(index);
        storage.save(tasks);
        return ui.showDone(tasks.getTask(index));
    }

}

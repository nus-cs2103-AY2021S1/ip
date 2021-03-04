package olivia.command;

import java.time.format.DateTimeParseException;
import java.util.List;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Deadline;
import olivia.task.Task;
import olivia.ui.Ui;

/**
 * DeadlineCommand class that represents a Deadline task being added to the
 * TaskList.
 */

public class DeadlineCommand implements Command {

    /**
     * Creates and stores a Deadline object in the TaskList if the input is valid.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command.
     * @return output String to the user.
     */

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        Storage storage = wrapper.getStorage();
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        Task task;
        StringBuilder description = new StringBuilder();
        StringBuilder time = new StringBuilder();
        if (input.size() == 0) {
            return "A deadline requires a description and a time!";
        }
        int i = 0;
        while (!input.get(i).equals("/by")) {
            description.append(input.get(i++)).append(" ");
            if (i == input.size()) {
                return "deadline requires the use of \"/by\"!";
            }
        }
        if (description.length() == 0) {
            return "The description of a deadline cannot be empty!";
        }
        while (++i < input.size()) {
            time.append(input.get(i)).append(" ");
        }
        if (time.length() == 0) {
            return "The time of a deadline cannot be empty!";
        }
        description.deleteCharAt(description.length() - 1);
        time.deleteCharAt(time.length() - 1);
        try {
            task = new Deadline(description.toString(), time.toString(), false);
            tasks.addTask(task);
            storage.save(tasks);
            return ui.showAdd(tasks, task);
        } catch (DateTimeParseException e) {
            return "Error: Please use the following format instead:\ndd-MM-yyyy HHmm";
        }
    }

}

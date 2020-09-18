package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class GetRemindersCommand extends Command {
    public static final String COMMAND_WORD = "getReminders";

    public GetRemindersCommand() {
        super("GetReminders command called", false);
    }

    /**
     * Executes the command.
     * Searches the list of tasks for tasks that have reminders set and returns them.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null;
        assert storage != null;
        try {
            ArrayList<Task> listOfTasks = getTasksWithReminders(taskList);
            storage.saveData(taskList, ui);
            return ui.displayTasksWithReminders(listOfTasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        }
    }

    private ArrayList<Task> getTasksWithReminders(TaskList taskList) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            if (task.getReminderStatus()) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }
}

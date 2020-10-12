package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a getEvents command when the user
 * wants to search for tasks that have a particular due date.
 */
public class GetEventsCommand extends Command {
    public static final String COMMAND_WORD = "getEvents";
    private LocalDate localDate;

    /**
     * Creates an instance of a GetEvents command with the appropriate
     * date the user is looking for.
     *
     * @param localDate Date which user wants to search for.
     */
    public GetEventsCommand(LocalDate localDate) {
        super("GetEvents command called", false);
        this.localDate = localDate;
    }

    /**
     * Executes the command.
     * Searches the list of tasks for tasks that have a due date
     * matching the date given as input and returns them.
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
            ArrayList<Task> listOfTasks = getTasksMatchingDate(taskList, this.localDate);
            storage.saveData(taskList, ui);
            return ui.displayEventsOnDate(listOfTasks, localDate);
        } catch (DateTimeParseException e) {
            return ui.showError("Please input a valid date format");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        }
    }

    private ArrayList<Task> getTasksMatchingDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            if (task.hasDate() && task.getDate().isEqual(localDate)) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }
}

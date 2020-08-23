package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

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
     * matching the date given as input and prints them.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            ArrayList<Task> listOfTasks = new ArrayList<>();
            for (int i = 0; i < taskList.getListSize(); i++) {
                Task task = taskList.getTaskAtIndex(i);
                if (task.hasDate() && task.getDate().isEqual(localDate)) {
                    listOfTasks.add(task);
                }
            }
            ui.displayEventsOnDate(listOfTasks, localDate);
        } catch (DateTimeParseException e) {
            ui.showError("Please input a valid date format");
        }
    }
}

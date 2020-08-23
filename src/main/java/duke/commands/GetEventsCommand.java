package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class GetEventsCommand extends Command {
    public static final String COMMAND_WORD = "getEvents";
    private LocalDate localDate;

    public GetEventsCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

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

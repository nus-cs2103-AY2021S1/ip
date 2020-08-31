package sparrow.commands;

import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_FIND_SUCCESS = "I found tasks matching your search: \n%s";

    public static final String MESSAGE_FIND_FAILURE = "I didn't find any matching tasks :(";

    private final LocalDate date;

    public FilterCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.filterList(date);
        if (matchingTasks.size() == 0) {
            return MESSAGE_FIND_FAILURE;
        } else {
            String matchingTasksAsString = ui.taskListToString(matchingTasks);
            return String.format(MESSAGE_FIND_SUCCESS, matchingTasksAsString);
        }
    }

}

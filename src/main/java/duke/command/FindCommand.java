package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.util.TaskList;

public class FindCommand extends Command {

    protected static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";
    protected static final String NONE_FOUND_MESSAGE = "It seems there were no matches!";

    protected final String searchString;

    private FindCommand(String searchString) {
        this.searchString = searchString;
    }

    public static FindCommand parse(String searchString) {
        String[] details = searchString.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a keyword/keyphrase to search!");
        }
        return new FindCommand(details[1]);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTasks = getMatchingTasks(searchString, taskList);
        ui.outputMessage(createFindMessage(matchingTasks));
    }

    protected TaskList getMatchingTasks(String searchString, TaskList taskList) {
        TaskList matchingList = new TaskList();
        for (Task task : taskList) {
            if (task.getTaskDescription().contains(searchString)) {
                matchingList.add(task);
            }
        }
        return matchingList;
    }

    protected String createFindMessage(TaskList taskList) {
        if (taskList.size() == 0) {
            return NONE_FOUND_MESSAGE;
        } else {
            return FOUND_MESSAGE + '\n' + taskList.toString();
        }
    }




}

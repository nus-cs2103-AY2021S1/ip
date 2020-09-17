package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import duke.dukeexception.DukeException;

import java.util.ArrayList;
import java.util.List;


/**
 * Command that finds tasks that contain a given keyword when executed.
 */
public class FindCommand extends Command{
    /** Keyword to be searched for */
    private final String description;

    /**
     * Public constructor. Removes whitespaces of <code>description</code>
     * when assigning to class variable, and converts keyword to lower case
     * for simplicity.
     *
     * @param description Keyword that user wants to search for amongst tasks.
     */
    public FindCommand(String description) {
        this.description = description.replaceAll("\\s","").toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        List<Task> resultList = findMatchingTasks(tasks);

        String finalString = "";
        int counter = 0;
        for (Task task : resultList) {
            counter++;
            finalString += "\n" + counter + "." + task.toString();
        }

        return ui.returnFindReply(finalString);
    }

    private List<Task> findMatchingTasks(TaskList tasks) {
        List<Task> resultList = new ArrayList<>();

        for (int i = 1; i <= tasks.getListLength(); i++) {
            Task task = tasks.getTask(i);
            if (task.getTaskName().toLowerCase().contains(this.description)) {
                resultList.add(task);
            }
        }

        return resultList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

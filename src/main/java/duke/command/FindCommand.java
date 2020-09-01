package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

import duke.dukeexception.DukeException;

import java.util.ArrayList;
import java.util.List;


/**
 * Finds tasks that contain a given keyword when executed.
 */
public class FindCommand extends Command{
    /** Keyword to be searched for */
    private final String description;

    /**
     * Public constructor.
     *
     * @param description Keyword that user wants to search for amongst tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        List<Task> resultList = new ArrayList<>();

        for (int i = 1; i <= tasks.getListLength(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(task.getTaskName());
            if (task.getTaskName().contains(this.description)) {
                resultList.add(task);
            }
        }

        String finalString = "";
        int counter = 0;
        for (Task task : resultList) {
            counter++;
            finalString += "\n" + counter + "." + task.toString();
        }

        System.out.println("Na, I found this:" + finalString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.commands;


import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Command that finds tasks that contain the description given.
 */
public class FindCommand extends Command {

    String inputDescription;

    /**
     * Constructor.
     * @param remainingString The remaining input string apart from action.
     */
    public FindCommand(String remainingString) {
        this.inputDescription = remainingString.trim();
    }

    /**
     * Adds a Task to the TaskList and update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        ArrayList<Task> taskArray = new ArrayList<>();
        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(inputDescription)) {
                taskArray.add(task);
            }
        }

        TaskList matchedTaskList = new TaskList(taskArray);
        String outputString = ui.printFindMessage(matchedTaskList);
        outputString += super.execute(taskList, ui, storage);
        return outputString;
    }
}
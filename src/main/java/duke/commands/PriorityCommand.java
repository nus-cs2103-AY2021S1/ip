package duke.commands;

import duke.tasks.*;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

/**
 * Command that marks priority to existing tasks.
 */
public class PriorityCommand extends Command{
    int taskIndex;
    String priorityLevel;

    /**
     * Constructor
     * @param taskIndex Index of task to be marked with priority level.
     * @param priorityLevel Priority level to be marked.
     */
    public PriorityCommand(int taskIndex, String priorityLevel) {
        this.taskIndex = taskIndex;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Adds a Task to the TaskList and update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @return outputString Command output.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task taskToMark = taskList.get(taskIndex);
        taskToMark.setPriorityLevel(priorityLevel);
        taskList.set(taskIndex, taskToMark);
        String outputString = ui.printPriorityMessage(taskToMark);
        outputString += super.execute(taskList, ui, storage);
        return outputString;
    }
}

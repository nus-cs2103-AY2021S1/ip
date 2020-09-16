package command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * RepeatCommand will cause the task to be recurring.
 *
 * @author Joshua
 */
public class RepeatCommand extends Command {
    private int repeatPosition;
    private Parser.FrequencyOfRecurrence frequency;

    /**
     * Creates a RepeatCommand with the position of the task to be changed and the frequency it
     * should be changed to.
     *
     * @param repeatPosition the position of the task in the task list.
     * @param frequency the frequency it should be set to recur.
     */
    public RepeatCommand(int repeatPosition, Parser.FrequencyOfRecurrence frequency) {
        this.repeatPosition = repeatPosition;
        this.frequency = frequency;
    }

    /**
     * Executes the RepeatCommand with the following TaskList, Ui and Storage classes.
     * The newly recurring task will be added to the TaskList. The Ui will return the output
     * to the user. The storage will update with the new TaskList.
     *
     * @param taskList the TaskList to add the input task to.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is to be updated.
     * @return the output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTaskList().get(repeatPosition);
        task.setRepeated(frequency);
        output = ui.showAdded(task) + "\n";
        output = output + ui.showNumberInList(taskList);
        storage.updateStorage(taskList);
        return output;
    }
}

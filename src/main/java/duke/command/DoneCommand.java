package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;



public class DoneCommand extends Command {
    private String taskIdString;

    public DoneCommand(String taskIdString) {
        this.taskIdString = taskIdString;
    }

    /**
     * Sets specified duke.task in duke.tasklist as completed and updates Storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.taskIdString);
            if (taskId <= 0 || taskId > taskList.size()) {
                throw new DukeException("That task does not exist!");
            } else {
                taskList.get(taskId - 1).setCompleted();
                storage.save(taskList);
                String response = "Congrats! I've marked this task as done: \n" + taskList.get(taskId - 1);
                return response;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please specify which task you have completed!");
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify which task you have completed!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

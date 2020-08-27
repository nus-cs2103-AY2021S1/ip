package command;

import exception.DukeException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class DeleteCommand extends Command {
    private String taskIdString;

    public DeleteCommand(String taskIdString) {
        this.taskIdString = taskIdString;
    }

    /**
     * Deletes specified task from tasklist and updates Storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(taskIdString);
            if (taskId <= 0 || taskId > taskList.size()) {
                throw new DukeException(ui.LINE + "Invalid input! That task does not exist! \n" + ui.LINE);
            } else {
                int new_size = taskList.size() - 1;
                System.out.println(ui.LINE + "Noted! I've deleted this task: \n"
                        + taskList.get(taskId - 1) + "\n"
                        + "Now you have " + new_size + " tasks in the list."
                        + "\n" + ui.LINE);
                taskList.delete(taskId - 1);
            }
            storage.save(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please specify which task you want to delete! \n" + ui.LINE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

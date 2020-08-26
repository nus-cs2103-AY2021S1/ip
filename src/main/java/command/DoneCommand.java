package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private String taskIdString;

    public DoneCommand(String taskIdString) {
        this.taskIdString = taskIdString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.taskIdString);
            if (taskId <= 0 || taskId > taskList.size()) {
                throw new DukeException(ui.LINE + "Invalid input! That task does not exist! \n" + ui.LINE);
            } else {
                taskList.get(taskId - 1).setCompleted();
                System.out.println(ui.LINE + "Nice! I've marked this task as done: \n"
                        + taskList.get(taskId - 1) + "\n" + ui.LINE);
            }
            storage.save(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please specify which task you have completed! \n" + ui.LINE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

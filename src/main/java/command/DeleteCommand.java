package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private String taskIdString;

    public DeleteCommand(String taskIdString) {
        this.taskIdString = taskIdString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <=0 || taskId > taskList.size()) {
            System.out.println(ui.LINE + "Invalid input! That task does not exist! \n" + ui.LINE);
        } else {
            int new_size = taskList.size() - 1;
            System.out.println(ui.LINE + "Noted! I've deleted this task: \n"
                    + taskList.get(taskId - 1) + "\n"
                    + "Now you have " + new_size + " tasks in the list."
                    + "\n" + ui.LINE);
            taskList.delete(taskId - 1);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

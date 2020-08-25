package Commands;

import Storage.Storage;
import Ui.Ui;
import exceptions.DukeException;
import task.Task;
import task.TaskList;


public class UpdateCommand extends Command {
    private int index;
    public UpdateCommand(String command, String index) {
        super(command);
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task selectedTask = taskList.getTask(index);
            selectedTask.setDone();
            ui.printDoneMessage(selectedTask);
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
        }
    }

}

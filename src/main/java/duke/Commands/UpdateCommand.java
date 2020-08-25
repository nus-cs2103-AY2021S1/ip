package duke.Commands;

import duke.Storage.Storage;
import duke.Ui.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;


public class UpdateCommand extends Command {
    private int index;
    public UpdateCommand(String command, String index) {
        super(command);
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid duke.task number");
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

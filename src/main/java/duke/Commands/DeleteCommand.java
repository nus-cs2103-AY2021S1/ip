package duke.Commands;

import duke.Storage.Storage;
import duke.Ui.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;


public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String command, String index) {
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
            Task selectedTask = taskList.deleteTask(index);
            ui.printDeleteMessage(taskList, selectedTask);
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
        }
    }

}

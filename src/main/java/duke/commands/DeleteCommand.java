package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommand;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private int itemIndex;
    public DeleteCommand (int itemIndex) {
        super();
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        try {
            Task removedTask = taskList.removeTask(itemIndex - 1);
            listStorage.deleteTask(removedTask);
            ui.deleteTask(removedTask, taskList);
        } catch(IndexOutOfBoundsException ex) {
            try {
                throw new InvalidCommand("Please enter a valid task number.");
            } catch (InvalidCommand invalidCommand) {
                invalidCommand.printStackTrace();
            }
        }
    }
}

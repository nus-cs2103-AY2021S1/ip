package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeleteException;
import duke.exception.DukeException;

public class DeleteCommand extends Command {

    public DeleteCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processDelete(this.task, taskList, ui, storage);
        } catch (DeleteException d) {
            System.out.println(d.getMessage());
        }
    }

    public void processDelete(String theRest, TaskList taskList, Ui ui, Storage storage) throws DeleteException {
        try {
            Integer taskNum = Integer.parseInt(theRest);
            int index = taskNum - 1;
            taskList.deleteTask(index);
            storage.updateData(taskList.getTasks());

        } catch (DukeException d) {
            throw new DeleteException("Please enter a number. I cannot delete nothing :(");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) other;
            return this.task.equals(deleteCommand.getTask());
        }
        return false;
    }
}

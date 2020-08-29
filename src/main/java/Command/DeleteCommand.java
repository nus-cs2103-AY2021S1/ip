package command;

import exceptions.MissingSpecifiedDeleteError;
import exceptions.WrongIndexError;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class DeleteCommand extends Command {

    /**
     * Initializes DeleteCommand
     *
     * @param command
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MissingSpecifiedDeleteError, WrongIndexError {
        try {
            tasks = tasks.delete(this.command);
            ui.showDeletedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
        } catch (MissingSpecifiedDeleteError e) {
            throw new MissingSpecifiedDeleteError(e.getMessage());
        } catch (WrongIndexError e) {
            throw new WrongIndexError(e.getMessage());
        }
    }

    /**
     * Check if the current command is an exit command
     **/
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Override the equals method from Object to handle DeleteCommand
     **/
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand temp = (DeleteCommand) o;
            if (temp.command.equals(this.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

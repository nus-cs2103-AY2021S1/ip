package command;

import exceptions.InvalidCommandException;
import exceptions.NoDateException;
import exceptions.NoTaskException;
import exceptions.WrongDateTimeFormatException;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class AddCommand extends Command {

    /**
     * Initializes AddCommand class
     *
     * @param command
     */
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Executes the command
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDateException, InvalidCommandException,
        NoTaskException, WrongDateTimeFormatException {
        try {
            tasks = tasks.add(this.command);
            ui.showAddedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
        } catch (WrongDateTimeFormatException e) {
            throw new WrongDateTimeFormatException(e.getMessage());
        }
    }

    /**
     * Check if the current command is an exit command
     **/
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand temp = (AddCommand) o;
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

package command;

import exceptions.DukeException;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class ExitCommand extends Command {

    /**
     * Initializes ExitCommand
     *
     * @param command
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Executes the command
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.putToDatabase(tasks.getTaskList());

        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return ui.showGoodBye();
    }

    /**
     * Check if the current command is an exit command
     **/
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Override the equals method from Object to handle ExitCommand
     **/
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ExitCommand) {
            ExitCommand temp = (ExitCommand) o;
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

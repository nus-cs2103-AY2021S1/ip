package clippy.command;

import clippy.storage.Storage;

import clippy.task.TaskList;

import clippy.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    
    /**
     * Returns bye message.
     * Executes the command.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Bye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }
}

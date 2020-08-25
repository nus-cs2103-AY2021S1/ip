package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates a command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        //The bot says bye and the program terminates
        ui.sayBye();
    }

    @Override
    public boolean equals(Object obj) {
        //Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /*Check if obj is an instance of this class.
          All ExitCommand instances are equal.
         */
        return obj instanceof ExitCommand;
    }
}

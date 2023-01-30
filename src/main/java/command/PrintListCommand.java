package command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;



/**
 * Represents a command to print the list of all task.
 */
public class PrintListCommand extends Command {

    public PrintListCommand(String[] command) {
        super(command);
    }

    /**
     * Print the list of the task.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printList();
    }

    /**
     * Indicates not to exit the loop.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof PrintListCommand) {
            PrintListCommand cur = (PrintListCommand) o;
            if (Arrays.equals(this.commands, cur.commands)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

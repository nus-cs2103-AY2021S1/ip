package dude.command;

import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

import java.io.IOException;

/**
 * The command changes the status of an incomplete task to complete.
 */

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String action, int index) {
        super(action);
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException  {
        try {
            tasks.getTask(index - 1).markAsDone();
            StringBuilder str = new StringBuilder();
            str.append("Solid bro!! I've marked this task as done:\n").append(
                    tasks.getTask(index - 1).toString() + "\n");
            ui.setMessage(str.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch(IndexOutOfBoundsException e) {
            throw new CommandException("Sorry! The task index you wanted to complete does not exist!");
        }
    }
}

package dude.command;

import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String action, int index) {
        super(action);
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException  {
        try {
            StringBuilder str = new StringBuilder();
            str.append("Understood. I've removed this task:\n  ").append(
                    tasks.getTask(index - 1).toString()).append("Now you have ").append(
                    tasks.getCount() - 1).append(" tasks in the list.");
            tasks.deleteTask(index - 1);
            ui.setMessage(str.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Sorry! The index to be removed does not exist!");
        }
    }
}

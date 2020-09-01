package dude.command;

import java.io.IOException;

import dude.task.Todo;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

/**
 * The command creates a todo task and adds it to the tasklist when executed.
 */

public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for the TodoCommand class.
     * @param action action the command is to perform.
     * @param description description of the task.
     */
    public TodoCommand(String action, String description) {
        super(action);
        this.description = description;
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        try {
            StringBuilder str = new StringBuilder();
            Todo current = new Todo(description);
            tasks.addTask(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(tasks.getCount()).append(" tasks in the list.");
            ui.setMessage(str.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (Exception e) {
            throw new CommandException("Sorry, ToDo does not accept this argument!");
        }
    }
}

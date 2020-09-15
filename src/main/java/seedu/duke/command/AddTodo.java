package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.exception.DukeCommandException;
import seedu.duke.ui.Ui;
import seedu.duke.task.ToDo;

/**
 * Class that represents adding a todo task.
 */
public class AddTodo extends Command {
    public AddTodo(String[] words) {
        super(words);
    }

    /**
     * Adds the task to the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @exception DukeCommandException If nothing is written after the command.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeCommandException {
        try {
            ToDo newTD = new ToDo(words[1], false);
            ls.add(newTD);
            String thing = "Alright then, add more things to your ever-growing list of tasks:\n" +
                    newTD.getStatus().replaceAll("(?m)^", "\t") +
                    "\nNow you have " + ls.size() + " tasks in the list.";
            ui.printResult(thing);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCommandException("Write something after the command, gee.");
        }
    }
}

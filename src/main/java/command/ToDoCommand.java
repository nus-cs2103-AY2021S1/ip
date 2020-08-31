package command;

import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.TaskList;
import tasks.ToDo;

/**
 * Adds the todos entry into logic.Duke's list.
 */
public class ToDoCommand extends Command {
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to add the todos entry.
     *
     * @param tasks List of tasks given.
     * @param ui Handles the output to print.
     * @param storage Writes the save file.
     * @throws InvalidInputException If input of todos is wrong.
     * @throws InvalidSaveFileException If writing the save file goes wrong.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {

        final int INPUT_INDEX = 5;

        //Check if input is empty.
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo task = new ToDo(super.input.substring(INPUT_INDEX));
        tasks.getTasks().add(task);
        ui.printOutput("\tGot it. I've added this task:\n" + "\t" + task.toString()
                + "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }
}

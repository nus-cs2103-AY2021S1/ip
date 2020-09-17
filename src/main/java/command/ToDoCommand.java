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
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidSaveFileException {

        final int INPUT_INDEX = 5;
        final String TASK_DESCRIPTION = super.input;

        //Check if input is empty.
        if (TASK_DESCRIPTION.length() <= INPUT_INDEX || TASK_DESCRIPTION.substring(INPUT_INDEX).isBlank()) {
            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        //Check if there is a description overlap
        String taskDescription = super.input.substring(INPUT_INDEX);
        tasks.checkDuplicates(taskDescription);

        ToDo task = new ToDo(taskDescription);
        tasks.addTask(task);
        storage.saveFile(tasks.getTasks());
        return ui.printOutput("Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

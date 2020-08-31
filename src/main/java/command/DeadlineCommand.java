package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeFormatException;
import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.Deadline;
import tasks.TaskList;

/**
 * Adds the deadline entry that the user input to the
 * Arraylist of logic.Duke.
 */
public class DeadlineCommand extends Command {

    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final int deadlineIndex = 3;
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Adds deadline entry to the Arraylist.
     *
     * @param tasks List of tasks given.
     * @param ui Handles the output to print.
     * @param storage Writes the save file.
     * @throws InvalidDateTimeFormatException If input does not follow format specified.
     * @throws InvalidInputException If the input for the delete is incorrect.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeFormatException,
            InvalidInputException, InvalidSaveFileException {
        final int INPUT_INDEX = 9;

        //Check if the description is empty
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        final int DEADLINE_INDEX = 3;
        String[] splitWord = super.input.split("/");

        String desc = splitWord[0].substring(INPUT_INDEX, splitWord[0].length() - 1);
        String deadline = splitWord[1].substring(DEADLINE_INDEX);
        Deadline task;
        try {
            task = new Deadline(desc, LocalDateTime.parse(deadline, dateTimeFormat));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException(
                    "\tDeadline input must follow a certain format: yyyy-mm-dd HH:mm "
                    + "e.g. 2020-08-23 16:45");
        }
        tasks.addTask(task);
        ui.printOutput("\tGot it. I've added this task:\n" + "\t" + task.toString()
                + "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }
}

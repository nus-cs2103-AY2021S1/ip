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

    private final int INPUT_INDEX = 9;
    private final int DEADLINE_INDEX = 3;
    private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeFormatException,
            InvalidInputException, InvalidSaveFileException {

        //Run through a series of validity checks to ensure input is accurate
        checkValidity();

        String[] splitWord = super.input.split("/");
        String desc = splitWord[0].substring(INPUT_INDEX, splitWord[0].length() - 1);
        String deadline = splitWord[1].substring(DEADLINE_INDEX);
        Deadline task;
        tasks.checkDuplicates(desc);

        try {
            task = new Deadline(desc, LocalDateTime.parse(deadline, dateTimeFormat));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException(
                    "Deadline input must follow a certain format: yyyy-mm-dd HH:mm "
                            + "e.g. 2020-08-23 16:45");
        }

        tasks.addTask(task);
        storage.saveFile(tasks.getTasks());
        return ui.printOutput("Got it. I've added this task:\n" + task.toString()
                + "\n\nNow you have " + tasks.getTasks().size() + " tasks in the list.");

    }

    private void checkValidity() throws InvalidInputException {

        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("The description of a deadline cannot be empty.");
        }

        String[] splitWord = super.input.split("/");
        String description = splitWord[0].substring(INPUT_INDEX);

        if (description.isBlank()) {
            throw new InvalidInputException("The description of a deadline cannot be empty.");
        } else if (splitWord.length != 2) {
            throw new InvalidInputException("Please use /by to indicate deadline, and only 1 forward slash throughout");
        } else if (description.charAt(description.length() - 1) != ' ') {
            throw new InvalidInputException("The formatting of your entry is wrong. Be sure to leave"
                    + " a space between the description and deadline");
        } else if (splitWord[1].length() < DEADLINE_INDEX
                || !splitWord[1].substring(0, DEADLINE_INDEX).equals("by ")) {
            throw new InvalidInputException("Please indicate the start of the deadline by using /by exclusively."
            + " Deadline format: yyyy-mm-dd HH:mm e.g. 2020-08-23 16:45");
        }
    }
}

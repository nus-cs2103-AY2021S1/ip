package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Deadline Command class to execute command that adds a deadline to
 * the TaskList.
 */
public class DeadlineCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";
    public static final String MESSAGE_PARSE_ERROR = "Invalid date and time format.\n"
            + "Please enter date and time in the format: yyyy-MM-dd HH:mm";

    public DeadlineCommand(String input) {
        super(input);
    }

    private boolean isValidLength(String input) {
        return input.length() <= 8;
    }
    private boolean containsKeyword(String input) {
        return input.contains("/by");
    }

    /**
     * Execute a Deadline Command to add a Deadline into TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException rejects empty deadline or incorrect date and time format.
     * @throws InvalidFileException throws an error when method fails to save.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (isValidLength(super.input)) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.\n");
        }
        if (!containsKeyword(super.input)) {
            throw new InvalidInputException("OOPS!!! Please use '/by' keyword\n");
        }
        try {
            assert super.input.contains("/by") : "Deadline does not have keyword command.";
            String[] split = super.input.substring(9).split("/by ", 2);
            if (split.length < 2) {
                throw new InvalidInputException("OOPS!!! Input format is incorrect!\n");
            }
            DateTimeFormatter formatter = Storage.FORMATTER;
            LocalDateTime date = LocalDateTime.parse(split[1], formatter);
            Task deadline = new Deadline(split[0], date);
            tasks.addTask(deadline);
            storage.save(tasks);
            return ui.printMessage(MESSAGE_SUCCESS + deadline.toString()
                    + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");

        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_PARSE_ERROR);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

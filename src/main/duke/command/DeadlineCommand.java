package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Deadline;


/**
 * Represents an request by the user to add a Deadline object to the list of Tasks.
 */
public class DeadlineCommand extends Command {

    private final Deadline deadline;

    /**
     * Constructor for a DeadlineCommand object.
     *
     * @param input the input string to be parsed into the arguments for constructing a Deadline object.
     * @throws DukeArgumentException if the arguments from the input string are invalid.
     */
    public DeadlineCommand(String input) throws DukeArgumentException {
        assert !input.isBlank() : "Input is empty.";
        String[] args = input.split("/by ", 2);
        try {
            String name = args[0].trim();
            LocalDate date = LocalDate.parse(args[1].trim());
            deadline = new Deadline(name, date);
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeArgumentException("Insufficient arguments provided for Deadline.");
        } catch (DateTimeParseException de) {
            throw new DukeArgumentException("Deadline date/time could not be parsed.");
        }
    }

    /**
     * Attempts to add the Deadline object to the storage.
     *
     * @param storage The Storage object to take in the new Deadline object
     * @throws DukeExecutionException if an IOException occurs
     */
    @Override
    public String execute(Storage storage) throws DukeExecutionException {
        try {
            storage.add(deadline);
            return String.format("Added %s.", deadline.toString());
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}

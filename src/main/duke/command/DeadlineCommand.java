package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.ui.Ui;


/**
 * Represents an request by the user to add a Deadline object to the list of Tasks.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    /**
     * Constructor for a DeadlineCommand object.
     *
     * @param input the input string to be parsed into the arguments for constructing a Deadline object.
     * @throws DukeArgumentException if the arguments from the input string are invalid.
     */
    public DeadlineCommand(String input) throws DukeArgumentException {
        String[] args = input.split("/by ", 2);
        try {
            this.deadline = new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()));
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeArgumentException("Insufficient arguments provided for duke.Tasks.Deadline.");
        } catch (DateTimeParseException de) {
            throw new DukeArgumentException("duke.Tasks.Deadline date/time could not be parsed.");
        }
    }

    @Override
    public boolean shouldExit() {
        return super.shouldExit();
    }

    /**
     * Attempts to add the Deadline object to the storage.
     *
     * @param storage The Storage object to take in the new Deadline object
     * @throws DukeExecutionException if an IOException occurs
     */
    @Override
    public void execute(Storage storage) throws DukeExecutionException {
        try {
            storage.add(deadline);
            Ui.showTaskAddition(deadline);
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}

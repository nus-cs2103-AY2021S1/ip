package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;


/**
 * Represents an request by the user to add a Event object to the list of Tasks.
 */
public class EventCommand extends Command {

    private Event event;


    /**
     * Constructor for a EventCommand object.
     *
     * @param input the input string to be parsed into the arguments for constructing a Event object.
     * @throws DukeArgumentException if the arguments from the input string are invalid.
     */
    public EventCommand(String input) throws DukeArgumentException {
        String[] args = input.split("/at ", 2);
        try {
            this.event = new Event(args[0].trim(), LocalDate.parse(args[1].trim()));
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeArgumentException("Insufficient arguments provided for duke.Tasks.Event.");
        } catch (DateTimeParseException de) {
            throw new DukeArgumentException("duke.Tasks.Event date/time could not be parsed.");
        }
    }

    @Override
    public boolean shouldExit() {
        return super.shouldExit();
    }

    /**
     * Attempts to add the Event object to the storage.
     *
     * @param storage The Storage object to take in the new Event object
     * @throws DukeExecutionException if an IOException occurs
     */
    @Override
    public void execute(Storage storage) throws DukeExecutionException {
        try {
            storage.add(event);
            Ui.showTaskAddition(event);
        } catch (IOException e) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }
    }
}

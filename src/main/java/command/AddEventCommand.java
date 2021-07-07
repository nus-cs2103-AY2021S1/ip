package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DuplicateTaskException;
import exception.EmptyEventException;
import task.Event;
import task.Task;
import ui.Ui;

/**
 * Represents a <code>Command</code> whose task is adding an <code>Event</code> to the <code>TaskList</code>.
 * The <code>AddEventCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class AddEventCommand extends Command {

    public AddEventCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Adds the <code>Event</code> to <code>tasks</code> and saves the <code>Event</code> to
     * <code>storage</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @return The response text.
     * @throws DukeException If failed to save to <code>storage</code>, no description provided in
     * <code>splitCommand</code>, or invalid date and time format (the date and time are
     * located inside the command argument).
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String argument = splitCommand[1];
            String description = argument.split(" /at ", 2)[0];
            String deadline = argument.split(" /at ", 2)[1];
            Task toAdd = new Event(description, LocalDateTime.parse(deadline,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));

            if (tasks.contains(toAdd)) {
                throw new DuplicateTaskException();
            }
            tasks.add(toAdd);
            storage.save(tasks);
            return ui.sayAddedTask(toAdd, tasks.size());
        } catch (IOException e) {
            return ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyEventException();
        } catch (DateTimeParseException e) {
            return ui.say("The date and time format must be: [dd/MM/yyyy HHmm]\nFor example, 02/12/2019 1800");
        } catch (DuplicateTaskException e) {
            throw e;
        }
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddEventCommand) {
            AddEventCommand other = (AddEventCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}

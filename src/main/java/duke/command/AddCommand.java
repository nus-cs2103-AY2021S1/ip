package duke.command;

import duke.*;
import duke.exception.*;
import duke.task.*;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    public AddCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeInvalidDateException,
            DukeInvalidArgumentException {
        Task taskToBeAdded = null;
        if (command.equals("todo")) {
            taskToBeAdded = new Todo(extra);
        }
        try {
            if (command.equals("deadline")) {
                taskToBeAdded = new Deadline(extra.substring(0, extra.indexOf("/") - 1),
                        extra.substring(extra.indexOf("/") + 4));
            }
            if (command.equals("event")) {
                taskToBeAdded = new Event(extra.substring(0, extra.indexOf("/") - 1),
                        extra.substring(extra.indexOf("/") + 4));
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException(command);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException(command);
        }
        tasks.addTask(taskToBeAdded);
        ui.printAdded(taskToBeAdded, tasks.getSize());
    }
}

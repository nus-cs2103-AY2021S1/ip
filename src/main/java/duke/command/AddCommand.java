package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Arrays;

/**
 * A command dealing with adding an entry to a Duke-list.
 */
public class AddCommand extends Command {
    private CommandType commandType;
    private String[] details;

    /**
     * Constructs a new AddCommand object of the specified CommandType containing the accompanying details.
     *
     * @param commandType The type of the AddCommand
     * @param details The accompanying details needed for the execution of the command.
     */
    public AddCommand(CommandType commandType, String... details) {
        this.commandType = commandType;
        this.details = details;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        switch (commandType) {
        case TODO:
            assert details.length == 1;
            TaskList tasks = storage.getTasks();
            ToDo toDo = new ToDo(details[0]);
            tasks.addEntry(toDo);
            storage.addLine(toDo.toFileString());
            ui.showAddedTask(toDo.toString(), tasks.getNumEntries());
            break;
        case DEADLINE:
            assert details.length == 2;
            tasks = storage.getTasks();
            Deadline deadline = new Deadline(details[0], details[1]);
            tasks.addEntry(deadline);
            storage.addLine(deadline.toFileString());
            ui.showAddedTask(deadline.toString(), tasks.getNumEntries());
            break;
        case EVENT:
            assert details.length == 2;
            tasks = storage.getTasks();
            Event event = new Event(details[0], details[1]);
            tasks.addEntry(event);
            storage.addLine(event.toFileString());
            ui.showAddedTask(event.toString(), tasks.getNumEntries());
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
        };
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return commandType.toString() + " " + Arrays.toString(details);
    }
}

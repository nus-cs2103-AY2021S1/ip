package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Arrays;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output;
        switch (commandType) {
        case TODO:
            ToDo toDo = new ToDo(details[0]);
            tasks.addTask(toDo);
            storage.addLine(toDo.toFileString());
            output = ui.showAddedTask(toDo.toString(), tasks.getNumTasks());
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(details[0], details[1]);
            tasks.addTask(deadline);
            storage.addLine(deadline.toFileString());
            output = ui.showAddedTask(deadline.toString(), tasks.getNumTasks());
            break;
        case EVENT:
            Event event = new Event(details[0], details[1]);
            tasks.addTask(event);
            storage.addLine(event.toFileString());
            output = ui.showAddedTask(event.toString(), tasks.getNumTasks());
            break;
        default:
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
        return output;
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

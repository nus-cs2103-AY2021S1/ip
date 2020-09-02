package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Command {
    protected CommandType commandType;
    protected String[] inputs;
    protected boolean isExit;

    /**
     * Creates a Command object.
     * @param commandType Type of command given.
     * @param inputs Inputs related to the command given.
     */
    public Command(CommandType commandType, String[] inputs) {
        this.commandType = commandType;
        this.inputs = inputs;
    }

    /**
     * Returns whether the use is exiting the program.
     * @return Whether the user is exiting ths program.
     */
    public boolean isExit() {
        return isExit;
    }

    public String execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        switch (commandType) {
        case BYE:
            isExit = true;
            return ui.printBye();
        case HELP:
            return ui.printHelp();
        case LIST:
            return ui.showList(tasks);
        case DONE:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! duke.task.Task number cannot be empty for done action!");
            }
            try {
                Task doneTask = tasks.markTaskAsDone(Integer.parseInt(inputs[1]));
                storage.saveTaskListToFile(tasks);
                return ui.showMarkedAsDone(doneTask);
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new DukeException("OOPS! duke.task.Task number is invalid!");
            }
        case DELETE:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! duke.task.Task number cannot be empty for delete action!");
            }
            try {
                Task deleteTask = tasks.deleteTask(Integer.parseInt(inputs[1]));
                storage.saveTaskListToFile(tasks);
                return ui.showDeletedTask(deleteTask, tasks);
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new DukeException("OOPS! duke.task.Task number is invalid!");
            }
        case TODO:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! The description of a todo cannot be empty!");
            }
            Task addedToDo = tasks.addTask(new ToDo(inputs[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showAddedTask(addedToDo, tasks);
        case DEADLINE:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! The description of a deadline cannot be empty!");
            }
            String[] deadlineDetails = inputs[1].split(" /by ", 2);
            if (deadlineDetails.length <= 1) {
                throw new DukeException("OOPS! The date of a deadline cannot be empty!");
            }
            Task addedDeadline = tasks.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showAddedTask(addedDeadline, tasks);
        case EVENT:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! The description of an event cannot be empty!");
            }
            String[] eventDetails = inputs[1].split(" /at ", 2);
            if (eventDetails.length <= 1) {
                throw new DukeException("OOPS! The time of an event cannot be empty!");
            }
            Task addedEvent = tasks.addTask(new Event(eventDetails[0], eventDetails[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showAddedTask(addedEvent, tasks);
        case FIND:
            if (inputs.length <= 1) {
                throw new DukeException("OOPS! The keyword for search cannot be empty!");
            }
            return ui.showMatchingTasks(tasks, inputs[1]);
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }
}

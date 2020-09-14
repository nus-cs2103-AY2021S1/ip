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
     *
     * @param commandType Type of command given.
     * @param inputs Inputs related to the command given.
     */
    public Command(CommandType commandType, String[] inputs) {
        this.commandType = commandType;
        this.inputs = inputs;
    }

    /**
     * Returns whether the use is exiting the program.
     *
     * @return Whether the user is exiting ths program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes a command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
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
            return executeDoneCommand(ui, tasks, storage);
        case DELETE:
            return executeDeleteCommand(ui, tasks, storage);
        case TODO:
            return executeToDoCommand(ui, tasks, storage);
        case DEADLINE:
            return executeDeadlineCommand(ui, tasks, storage);
        case EVENT:
            return executeEventCommand(ui, tasks, storage);
        case FIND:
            return executeFindCommand(ui, tasks);
        case SNOOZE:
            return executeSnoozeCommand(ui, tasks, storage);
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes a Done command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeDoneCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("OOPS! Task number cannot be empty for done action!");
        }
        try {
            Task doneTask = tasks.markTaskAsDone(Integer.parseInt(inputs[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showMarkedAsDone(doneTask);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS! Task number is invalid!");
        }
    }

    /**
     * Executes a Delete command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeDeleteCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("OOPS! Task number cannot be empty for delete action!");
        }
        try {
            Task deleteTask = tasks.deleteTask(Integer.parseInt(inputs[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showDeletedTask(deleteTask, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS! Task number is invalid!");
        }
    }

    /**
     * Executes a ToDo command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeToDoCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("OOPS! The description of a todo cannot be empty!");
        }
        Task addedToDo = tasks.addTask(new ToDo(inputs[1]));
        storage.saveTaskListToFile(tasks);
        return ui.showAddedTask(addedToDo, tasks);
    }

    /**
     * Executes a Deadline command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeDeadlineCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
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
    }

    /**
     * Executes a Event command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeEventCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
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
    }

    /**
     * Executes a Find command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeFindCommand(Ui ui, TaskList tasks) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("OOPS! The keyword for search cannot be empty!");
        }
        return ui.showMatchingTasks(tasks, inputs[1]);
    }

    /**
     * Executes a Snooze command.
     *
     * @param ui The text UI of the program.
     * @param tasks The task list of the program.
     * @param storage The storage of the program.
     * @return Text to be displayed in GUI.
     * @throws DukeException When there is error in reading or executing the command.
     */
    public String executeSnoozeCommand(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException("OOPS! Task number cannot be empty for snooze action!");
        }
        try {
            Task snoozedTask = tasks.snoozeTask(Integer.parseInt(inputs[1]));
            storage.saveTaskListToFile(tasks);
            return ui.showSnoozedTask(snoozedTask, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS! Task number is invalid!");
        }
    }
}

package duke.commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Time;
import duke.Ui;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDeadlineFormatException;
import duke.exceptions.InvalidEventFormatException;
import duke.exceptions.InvalidTimeException;
import duke.exceptions.InvalidToDoFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Class that contains duke.commands that can add task to the task list.
 * Handles cases for event, todo and deadline commands.
 */
public class AddCommand extends Command {

    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes event, todo or deadline commands and makes sure that the format of the given
     * string is correct.
     * Throws InvalidCommandException if the command is unknown.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @return String message of the command.
     * @param storage Storage object to read or save the task list in the hardware.
     * @throws InvalidToDoFormatException If todo command format is wrong.
     * @throws InvalidDeadlineFormatException If deadline command format is wrong.
     * @throws InvalidTimeException If format of the input time given by the user is wrong.
     * @throws InvalidCommandException If command does not match event, todo or deadline.
     * @throws InvalidEventFormatException If event command format is wrong.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidToDoFormatException,
            InvalidDeadlineFormatException, InvalidTimeException, InvalidCommandException,
            InvalidEventFormatException {

        String[] tempArray = fullCommand.trim().split(" ");

        assert Parser.isToDoCommand(tempArray[0]) || Parser.isEventCommand(tempArray[0])
                || Parser.isDeadlineCommand(tempArray[0]) : "Invalid Command";

        if (Parser.isToDoCommand(tempArray[0])) {
            if (tempArray.length == 1) {
                throw new InvalidToDoFormatException();
            }
            String task = fullCommand.substring(5);
            ToDo newToDoTask = new ToDo(task);
            taskList.addTask(newToDoTask);
            return ui.taskMessage(newToDoTask.toString(), taskList.getTaskListLength());
        }

        if (Parser.isEventCommand(tempArray[0])) {
            if (tempArray.length == 1) {
                throw new InvalidEventFormatException();
            }
            String eventAndDate = fullCommand.substring(6);
            String[] eventDateArray = eventAndDate.trim().split(" /at ");
            if (eventDateArray.length != 2) {
                throw new InvalidEventFormatException();
            }
            Event newEventTask = new Event(eventDateArray[0], Time.getFormattedTime(eventDateArray[1]));
            taskList.addTask(newEventTask);
            return ui.taskMessage(newEventTask.toString(), taskList.getTaskListLength());
        }

        if (Parser.isDeadlineCommand(tempArray[0])) {
            if (tempArray.length == 1) {
                throw new InvalidDeadlineFormatException();
            }
            String deadlineAndDate = fullCommand.substring(9);
            String[] deadlineDateArray = deadlineAndDate.trim().split(" /by ");
            if (deadlineDateArray.length != 2) {
                throw new InvalidDeadlineFormatException();
            }
            Deadline newDeadlineTask = new Deadline(deadlineDateArray[0], Time.getFormattedTime(deadlineDateArray[1]));
            taskList.addTask(newDeadlineTask);
            return ui.taskMessage(newDeadlineTask.toString(), taskList.getTaskListLength());
        }

        throw new InvalidCommandException();
    }
}

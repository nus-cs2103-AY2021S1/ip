package commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Time;
import exceptions.InvalidCommandException;
import exceptions.InvalidDeadlineFormatException;
import exceptions.InvalidTimeException;
import exceptions.InvalidToDoFormatException;
import exceptions.InvalidEventFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class AddCommand extends Command{
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidToDoFormatException,
            InvalidDeadlineFormatException, InvalidTimeException, InvalidCommandException,
            InvalidEventFormatException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (Parser.isToDoCommand(tempArray[0])) {
            if (tempArray.length == 1) {
                throw new InvalidToDoFormatException();
            }
            String task = fullCommand.substring(5);
            ToDo newToDoTask = new ToDo(task);
            taskList.addTask(newToDoTask);
            ui.taskMessage(newToDoTask.toString(), taskList.getTaskListLength());
            return;
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
            ui.taskMessage(newEventTask.toString(), taskList.getTaskListLength());
            return;
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
            ui.taskMessage(newDeadlineTask.toString(), taskList.getTaskListLength());
            return;
        }

        throw new InvalidCommandException();
    }
}

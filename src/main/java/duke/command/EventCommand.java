package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add an Event task to the list.
 */
public class EventCommand extends Command {
    /**
     * A string array that represents the instructions of this command.
     */
    private String[] nextCommandArr;
    
    public EventCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Adds an Event task to the taskList.
     * @param taskList is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @throws DukeException if the instructions for the command is insufficient or not in the proper format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.nextCommandArr.length < 2) {
            throw new DukeException("The description of an event cannot be empty~");
        } else {
            try {
                String[] eventArr = this.nextCommandArr[1].split("/at");
                Event newEvent = new Event(eventArr[0], eventArr[1].strip());
                tasks.add(newEvent);
                ui.addTaskText(newEvent, tasks);
            } catch (Exception e) {
                throw new DukeException("Please input a proper date for your event~");
            }
        }
        
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}

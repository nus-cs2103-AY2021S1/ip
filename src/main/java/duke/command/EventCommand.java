package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

/** A command to add an @Event. */
public class EventCommand extends AddTaskCommand {

    /**
     * Constructs an EventCommand.
     *
     * @param input The description of the Event.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Adds an @Event to the task list.
     *
     * @param taskList The task list containing all saved tasks.
     * @param input    The description of the command.
     * @throws DukeException If there is an error when adding the Event into the list.
     */
    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addEvent(input);
    }

    /**
     * Compares an object.
     *
     * @param o The object compared.
     * @return True if the object is of type EventCommand and has the same @input.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof EventCommand) {
            EventCommand t = (EventCommand) o;
            return t.input.equals(this.input);
        } else {
            return false;
        }
    }
}

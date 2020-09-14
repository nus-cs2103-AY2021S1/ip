package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.parser.Parser;
import duke.TaskList;
import duke.task.Event;

/**
 * Command to create an event task.
 */
public class EventCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;
    private boolean isDone;

    /**
     * Initialise command to create an event to be
     * placed inside the task list.
     * @param fullCommand The user's full input of the event command.
     * @param taskList The list where the event task will be stored into.
     */
    public EventCommand(String fullCommand, TaskList taskList) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = false;
    }

    /**
     * Initialise command to create an event to be
     * placed inside the task list.
     * @param fullCommand The user's full input of the event command.
     * @param taskList The list where the event task will be stored into.
     * @param isDone The state of whether the Event is completed.
     */
    public EventCommand(String fullCommand, TaskList taskList, boolean isDone) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = isDone;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(5).length() <= 1) {
            throw new DukeException("The description of an event cannot be empty.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            String description = fullCommand.substring(6);
            String[] processedDesc = Parser.parseTimedTask(description);
            Event e = new Event(processedDesc[0], processedDesc[1], isDone);
            if (taskList.addTask(e)) {
                responseBuilder.add("Got it, I've added this event: " + e);
                responseBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
            } else {
                responseBuilder.add("Failed to add the event.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }

}

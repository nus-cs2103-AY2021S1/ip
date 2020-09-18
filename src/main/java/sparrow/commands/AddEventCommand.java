package sparrow.commands;

import sparrow.data.task.Event;

public class AddEventCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "event";

    public static final String REQUIREMENT_MESSAGE =
            "An event must include a description and a date, separated by /at";

    public AddEventCommand(Event toAdd) {
        super(toAdd);
    }
}

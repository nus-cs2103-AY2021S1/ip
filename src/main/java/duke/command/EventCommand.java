package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.EventParser;

public class EventCommand implements Command {
    private TaskList lines;
    private EventParser eventParser;

    /**
     * The constructor for the EventCommand object.
     * @param lines the TaskList to be manipulated.
     * @param eventParser the parser that parses the event command.
     */
    public EventCommand(TaskList lines, EventParser eventParser) {
        this.lines = lines;
        this.eventParser = eventParser;
    }

    /**
     * Executes the event command.
     * @return A string representing the output message to the user upon execution of the event command.
     */
    public String execute() {
        String message;
        try {
            String task = eventParser.checkIfValid();
            lines.addTask(task);
            message = Ui.addedTask(task, lines.getNumberOfItems());
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}


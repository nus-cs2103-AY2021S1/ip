package duke.commands;

import static duke.parser.Parser.parseDateTime;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.tasks.Event;
import duke.tasks.TaskList;



/**
 * A class that represents Event command.
 */
public class EventCommand {

    /**
     * Executes the Event command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static void executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(5).strip().equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] tempEventSplits = instruction.substring(5).strip().split("/at");
        if (tempEventSplits.length < 2) {
            throw new DukeException("The date and time of the event cannot be empty.");
        }
        String eventDescription = tempEventSplits[0].strip(); // clear the white spaces at the front and at the back
        String eventDateTime = tempEventSplits[1].strip(); // clear the white spaces at the front and at the back
        LocalDateTime eventDate = parseDateTime(eventDateTime);
        tasks.add(new Event(eventDescription, eventDate));
    }
}

package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.ToDoCommand;
import duke.common.DukeException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param command full user input string.
     * @return the command based on the user input.
     */
    public static Command parse(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        switch (parts[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            String doneTaskIndex = parts[1].trim();
            return new DoneCommand(Integer.parseInt(doneTaskIndex));
        case "delete":
            String deleteTaskIndex = parts[1].trim();
            return new DeleteCommand(Integer.parseInt(deleteTaskIndex));
        case "tag":
            String[] tag = parts[1].split(" ");
            String tagTaskIndex = tag[0].trim();
            String tagDesription = tag[1].trim();
            return new TagCommand(Integer.parseInt(tag[0]), tag[1]);
        case "find":
            String keyword = parts[1].trim();
            return new FindCommand(keyword);
        case "todo":
            String toDoDescription = parts[1].trim();
            return new ToDoCommand(toDoDescription);
        case "deadline":
            try {
                String[] deadline = parts[1].split("/by");
                String deadlineDescription = deadline[0].trim();
                String dateBy = deadline[1].trim();
                return new DeadlineCommand(deadlineDescription, dateBy);
            } catch (Exception e) {
                throw new DukeException("Deadline formatting is wrong! Please include /by.");
            }
        case "event":
            try {
                String[] event = parts[1].split("/at");
                String eventDescription = event[0].trim();
                String dateAt = event[1].trim();
                return new EventCommand(eventDescription, dateAt);
            } catch (Exception e) {
                throw new DukeException("Event formatting is wrong! Please include /at.");
            }

        default:
            throw new DukeException("Please try again!");
        }
    }
}

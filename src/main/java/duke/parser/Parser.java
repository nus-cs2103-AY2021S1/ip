package duke.parser;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ScheduleCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represent a parser to parse user input.
 */
public class Parser {
    /**
     * Return the output of Duke program after parsing the user input.
     * @param input user input
     * @param tasks task list
     * @param storage storage of the data of the program
     * @return output of the program as String
     */
    public String parse(String input, TaskList tasks, Storage storage) {
        assert input != null && tasks != null && storage != null;

        String[] args = input.split(" ", 2);

        try {
            switch (args[0]) {
            case "bye":
                return new ByeCommand().execute();
            case "list":
                return new ListCommand(tasks).execute();
            case "done":
                return new DoneCommand(args, tasks, storage).execute();
            case "todo":
                return new ToDoCommand(args, tasks, storage).execute();
            case "deadline":
                return new DeadlineCommand(args, tasks, storage).execute();
            case "event":
                return new EventCommand(args, tasks, storage).execute();
            case "delete":
                return new DeleteCommand(args, tasks, storage).execute();
            case "find":
                return new FindCommand(args, tasks).execute();
            case "schedule":
                return new ScheduleCommand(args, tasks).execute();
            default:
                return new ErrorCommand("OOPS!!! I'm sorry, but I don't know what that means :-(").execute();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

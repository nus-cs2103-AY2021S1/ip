package duke.parser;

import duke.command.*;
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
     * @throws DukeException exception thrown when input is invalid
     */
    public String parse(String input, TaskList tasks, Storage storage) throws DukeException {
        String[] command = input.split(" ", 2);

        switch (command[0]) { 
        case "bye":
            return new ByeCommand().execute();
        case "list":
                return new ListCommand(tasks).execute();
        case "done":
                return new DoneCommand(command, tasks, storage).execute();
        case "todo":
                return new ToDoCommand(command, tasks, storage).execute();
        case "deadline":
                return new DeadlineCommand(command, tasks, storage).execute();
        case "event":
                return new EventCommand(command, tasks, storage).execute();
        case "delete":
                return new DeleteCommand(command, tasks, storage).execute();
        case "find":
                return new FindCommand(command, tasks).execute();
        default:
                return new ErrorCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(").execute();
        }
    }
}

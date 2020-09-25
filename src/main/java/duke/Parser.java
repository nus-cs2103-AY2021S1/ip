package duke;

import duke.command.*;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalDeleteArgument;
import duke.exception.IllegalDoneArgument;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to understand user's command.
 * The essential functionality of this helper class is to map user command to
 * <code>Command</code> objects.
 */
public class Parser {

    /**
     * Map a response string to an executable <code>Command</code> object.
     * This methods map a response string to an executable <code>Command</code>
     * object. When converting, commands that Duke cannot understand will lead
     * to exception throwing.
     * @param response one-line input from the user
     * @return a <code>Command</code> object that Duke can execute
     * @throws Exception exceptions when Duke cannot understands the command or
     *         when the command does not have the required format.
     */
    public Command parse(String response) throws Exception {
        switch (response) {
        case "exit":
        case "bye":
            return new CommandBye();
        case "ls":
        case "list":
            return new CommandList();
        }

        String keyWord = response.split(" ")[0];

        switch (keyWord) {
            case "f":
            case "search":
            case "s":
            case "find":
                if (response.split(" ").length == 1) {
                    throw new NoDescriptionException("find");
                } else {
                    return new CommandFind(response.substring(5));
                }
            case "finish":
            case "done": {
                if (response.split(" ").length == 1) {
                    throw new NoDescriptionException("done");
                }
                if (response.split(" ").length != 2) {
                    throw new IllegalDoneArgument();
                }
                if (!response.split(" ")[1].matches("\\d+")) {
                    throw new IllegalDoneArgument();
                }
                int index = Integer.parseInt(response.split(" ")[1]) - 1;
                return new CommandDone(index);
            }
            case "rm":
            case "remove":
            case "dl":
            case "delete": {
                if (response.split(" ").length == 1) {
                    throw new NoDescriptionException("delete");
                }
                if (response.split(" ").length != 2) {
                    throw new IllegalDeleteArgument();
                }
                if (!response.split(" ")[1].matches("\\d+")) {
                    throw new IllegalDeleteArgument();
                }
                int index = Integer.parseInt(response.split(" ")[1]) - 1;
                return new CommandDelete(index);
            }
            case "st":
            case "sort": {
                if (!response.startsWith("sort /by")) {
                    throw new NoDescriptionException("sort");
                }
                String comparator = response.substring(9);
                return new CommandSort(comparator);
            }
            default:
                String firstCmd = response.split(" ")[0];
                Task newTask;
                switch (firstCmd) {
                    case "t":
                    case "td":
                    case "todo":
                        if (response.split(" ").length == 1) {
                            throw new NoDescriptionException("todo");
                        }
                        newTask = new Todo(response.substring(5));
                        break;
                    case "d":
                    case "ddl":
                    case "deadline": {
                        if (response.split(" ").length == 1) {
                            throw new NoDescriptionException("deadline");
                        }
                        String nameAndTime = response.substring(9);
                        if (nameAndTime.split(" /by ").length == 1) {
                            throw new NoTimeException("deadline");
                        }
                        newTask = new Deadline(response.substring(9).split(" /by ")[0],
                                response.substring(9).split(" /by ")[1]);
                        break;
                    }
                    case "e":
                    case "event": {
                        if (response.split(" ").length == 1) {
                            throw new NoDescriptionException("event");
                        }
                        String nameAndTime = response.substring(6);
                        if (nameAndTime.split(" /at ").length == 1) {
                            throw new NoTimeException("event");
                        }
                        newTask = new Event(response.substring(6).split(" /at ")[0],
                                response.substring(6).split(" /at ")[1]);
                        break;
                    }
                    default:
                        throw new IllegalCommandException(response);
                }
                return new CommandAdd(newTask);
        }
    }

}

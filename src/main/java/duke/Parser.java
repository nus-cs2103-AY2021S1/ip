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
        if (response.equals("bye")) {
            return new CommandBye();
        } else if (response.equals("list")) {
            return new CommandList();
        }

        String keyWord = response.split(" ")[0];

        if (keyWord.equals("find")) {
            if (response.split(" ").length == 1) {
                throw new NoDescriptionException("find");
            } else return new CommandFind(response.substring(5));
        } else if (keyWord.equals("done")) {
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
        } else if (keyWord.equals("delete")) {
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
        } else {
            String firstCmd = response.split(" ")[0];
            Task newTask;
            switch (firstCmd) {
                case "todo":
                    if (response.split(" ").length == 1) {
                        throw new NoDescriptionException("todo");
                    }
                    newTask = new Todo(response.substring(5));
                    break;
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

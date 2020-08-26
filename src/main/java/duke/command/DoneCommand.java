package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(in.replaceFirst("done ", ""));
            Task task = taskList.get(index - 1);
            task.markAsDone();
            return "Nice! I've marked this task as done\n  " + task.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please input a valid index");
        }
    }
}

package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand {
    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(in.replaceFirst("done ", "")) - 1;
            Task task = taskList.get(index);
            task.markAsDone();
            taskList.update(index);
            return "Nice! I've marked this task as done\n  " + task.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please input a valid index");
        }
    }
}

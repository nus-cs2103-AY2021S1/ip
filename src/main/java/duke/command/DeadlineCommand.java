package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

public class DeadlineCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException {
        String[] details = Parser.parseDeadline(input);
        String title = details[0];
        String by = details[1];
        Task task = new Deadline(title, by);
        taskList.add(task);
        ui.add(task);
    }
}

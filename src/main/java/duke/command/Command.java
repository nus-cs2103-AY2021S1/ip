package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

public interface Command {
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException;
}

package command;

import java.io.IOException;
import java.text.ParseException;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import storage.CommandStorage;

/**
 * Abstract Command class to handle users' command.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-01
 */
public abstract class Command {
    public abstract String execute(String inputMsg, TaskList currList, Ui ui, CommandStorage commandStorage)
            throws DukeException, ParseException, IOException;
}

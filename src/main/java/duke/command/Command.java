package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;

/**
 * Abstract command class.
 * Define method command class should have.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public abstract class Command {

    /**
     * Execute the logic define by the individual command type.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException exception that will occur running command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indication to exit and end the application.
     *
     * @return boolean true if application to end.
     */
    public abstract boolean isExit();
}

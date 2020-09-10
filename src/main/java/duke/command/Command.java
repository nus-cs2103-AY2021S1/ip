package duke.command;

import duke.action.Action;
import duke.task.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.exception.DukeException;

import java.util.Queue;

/**
 * A Command represents an instruction to be executed by Duke.
 */
public abstract class Command {

    /**
     * Identifies of the Command results in the termination of Duke program.
     */
    public abstract boolean isExit();

    /**
     * Contains the execution instructions specific to each Command.
     * @param ui Ui object initialized by Duke.
     * @param storage Storage object initialized by Duke.
     * @param tasks TaskList kept by Duke.
     * @param commandQueue
     */
    public abstract void execute(Ui ui, Storage storage, TaskList tasks, Queue<Action> commandQueue) throws DukeException;
}

package duke.command;

import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command.
 * @author Linngy.
 */
public interface Command {

    /**
     * Excutes a certain command.
     * @param tasks List of the tasks.
     * @param ui UI manager for Duke.
     * @param storage Manager of the file I/O.
     */
    void excute(TaskList tasks, Ui ui, Storage storage);
    boolean isExit();
}

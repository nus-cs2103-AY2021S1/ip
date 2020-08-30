/**
 * Encapsulates an idea of an executable object
 * based on a particular command and other information
 */
package duke.exec;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Executable {
    String run(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

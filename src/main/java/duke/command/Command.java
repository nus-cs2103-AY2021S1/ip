package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage);
}
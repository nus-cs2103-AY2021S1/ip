package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage);

    boolean isExit();
}
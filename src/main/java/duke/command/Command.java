package duke.command;

import duke.DukeException;
import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage,
                 MerchandiseList merchandises) throws DukeException;

}

package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

public class SortCommand implements Command {

    private final String sortBy;

    public SortCommand(String sortBy) throws DukeException {
        assert List.of("name", "type", "datetime").contains(sortBy)
                : "sortBy should only be name, type, or datetime.";
        if (!List.of("name", "type", "datetime").contains(sortBy)) {
            throw new DukeException("Invalid sorting parameter!");
        }
        this.sortBy = sortBy;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sort(sortBy);
        storage.update(tasks.getList());
        String successMessage = "Your list has been sorted!";
        ui.sendMessage(successMessage);
        return successMessage;
    }
}

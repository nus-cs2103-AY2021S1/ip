package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.logic.Tasklist;
import duke.logic.Storage;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        super();
        this.query = query;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) throws DukeException {
        if (query.isBlank()) {
            throw new DukeException(ErrorMessage.INVALID_QUERY.getMessage());
        }
        return "Here are the matching tasks in your list:\n"
                + tasks.matchedTasksOnly(query);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

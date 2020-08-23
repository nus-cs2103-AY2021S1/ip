package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class FindCommand implements Command{
    String taskSearch;

    public FindCommand(String taskSearch) {
        this.taskSearch = taskSearch.trim();
    }

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.findRelevantTask(taskSearch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof AddCommand;
    }
}

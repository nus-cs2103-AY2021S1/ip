package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {

    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.printTasksWithKeyword(word);
    }
}

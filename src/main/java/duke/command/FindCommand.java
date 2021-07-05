package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;


public class FindCommand extends Command {
    private final String input;
    public FindCommand(String input) {
        super(false);
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.search(tasks, input);
    }
}

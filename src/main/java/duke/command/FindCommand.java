package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTaskByKeyword(input.substring(5), tasks.getList());
    }

}
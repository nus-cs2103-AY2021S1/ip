package duke.command;

import duke.*;
import duke.task.TaskList;

public class GetCommand extends Command {

    private String input;

    public GetCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.findTask(Parser.parseDate(input.substring(9)), tasks.getList());
    }

}
package duke.command;

import duke.TaskList;
import duke.Ui;


public class AddCommand extends Command {

    public AddCommand() {
        super(CommandType.ADD);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printPrompt("What kind of task is it?\n"
                + " - Todo\n"
                + " - Deadline\n"
                + " - Event\n");
    }
}

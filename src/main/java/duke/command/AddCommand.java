package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;


public class AddCommand extends Command {

    public AddCommand() {
        super(CommandType.ADD, ImageType.PENDING);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printPrompt("What kind of task is it?\n"
                + " - Todo\n"
                + " - Deadline\n"
                + " - Event\n");
    }
}

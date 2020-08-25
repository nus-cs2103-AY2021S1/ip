package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ByeCommand extends UserCommand {

    public ByeCommand(String userInput) {
        super(userInput);
        this.isExit =true;
    }


    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.sayGoodBye();
    }
}

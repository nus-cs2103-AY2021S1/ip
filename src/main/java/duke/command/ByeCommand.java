package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that ends the program.
 */
public class ByeCommand extends UserCommand {

    /**
     * @param userInput user's input.
     */
    public ByeCommand(String userInput) {
        super(userInput);
        this.isExit = true;
    }


    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.sayGoodBye();
    }
}

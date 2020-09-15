package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command that ends the program.
 */
public class ByeCommand extends UserCommand {

    /**
     * @param userInput User's input.
     */
    public ByeCommand(String userInput) {
        super(userInput);
        this.isExit = true;
    }

    /**
     * @param taskList TaskList containing all the tasks.
     * @param ui Ui that helps to print output.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.sayGoodBye();
    }
}

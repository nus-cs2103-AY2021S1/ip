package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that displays all the Tasks in the TaskList.
 */
public class ListCommand extends UserCommand {

    /**
     * @param userInput User's input.
     */
    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printList(taskList);
    }
}

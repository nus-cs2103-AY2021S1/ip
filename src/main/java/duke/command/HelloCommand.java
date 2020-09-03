package duke.command;

import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents command to leave conversation.
 */
public class HelloCommand extends Command {

    public HelloCommand() {
        this.commandText = "hello";
    }

    /**
     * Leaves the conversation after saving the current state of tasks.
     *
     * @param text     unused argument.
     * @param taskList current list of tasks to be saved into hard disk.
     */
    @Override
    public String execute(String text, TaskList taskList) {
        return Ui.printHelloMessage();
    }
}

package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {

    /**
     * Constructor for the help command.
     * @param command
     */
    public HelpCommand(String command) {
        super(command, false);
    }

    /**
     * Executes the command to help the user based on specific task or generally to see all commands.
     *
     * @param list TaskList containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (command.length() > 4) {
            String specificHelp = command.substring(5);
            System.out.println("printing:" + specificHelp);
            ui.printHelpInstructions(specificHelp);
        } else {
            ui.generalHelp();
        }
    }

    /**
     * Executes the command to help the user based on specific task or generally to see all commands on FXML.
     *
     * @param list TaskList containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        if (command.length() > 4) {
            String specificHelp = command.substring(5);
            return ui.printHelpInstructions(specificHelp, true);
        } else {
            return ui.generalHelp(true);
        }
    }
}

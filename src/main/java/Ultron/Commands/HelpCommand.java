package ultron.commands;

import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.Storage;
import ultron.TaskList;
import ultron.UI;

public class HelpCommand extends Command {

    /**
     * Constructor for the Help Command.
     * @param arguments Argument for the help command
     */
    public HelpCommand(String arguments){
        super(false, arguments);
    }

    /**
     * Execute the help command.
     * @param taskList  List of tasks
     * @param ui        UI for Ultron
     * @param storage   Storage for Ultron
     * @throws UltronException if the number of arguments > 0
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0)
            throw new UltronException("list", ExceptionType.TOO_MUCH_ARGUMENTS);

        ui.print("Heh I guess I could help an insect like you:\n"
                + "- help                      : Get help for the commands\n"
                + "- todo (name)               : Adds a todo to the list\n"
                + "- event (name) /at (date)   : Adds an event at date\n"
                + "- deadline (name) /by (date): "
                + "Adds a deadline which expires by date\n"
                + "- delete (number)           : Removes a todo from the list\n"
        );
    }
}

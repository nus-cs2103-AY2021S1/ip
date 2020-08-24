package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;

public final class HelpCommand extends Command {

    public HelpCommand(final String arguments) {
        super(false, arguments);
    }

    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0) {
            throw new UltronException("list", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

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

package duke.tasks;

import java.io.IOException;

/**
 * Represents a help command. This command displays a list of
 * commands for the user.
 */
public class HelpCommand extends Command {
    private String helpList;

    /**
     * Constructor that stores the help string.
     */
    public HelpCommand() {
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //gets the list of help commands
        return tasks.help();
    }
}

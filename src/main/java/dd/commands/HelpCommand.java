package dd.commands;

import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item    Empty string.
     */
    public HelpCommand(String command, String item) {
        super(command, item);
    }

    /**
     * Use ui object to list all commands for user.
     *
     * @param taskList Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @return String to show all the possible DD commands.
     */
    @Override
    public String execute(TaskList taskList, Ui u, DataStorage ds) {
        return u.printAllCommands();
    }
}

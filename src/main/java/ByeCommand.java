/**
 * Represents a bye command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class ByeCommand extends Command {

    private final String EXIT_STRING = "Bye bye!!! See you again next time :)";

    /**
     * Executes the command.
     *
     * @param storage   The storage object that handles interactions with the
     *                  local file.
     * @param ui        The UI object that handles interactions with the user.
     * @param taskList  The current list of tasks.
     * @return          The string to be displayed to the user.
     * @throws DukeException    When the input command is invalid.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ui.printGoodbye();
        storage.write(taskList);
        return EXIT_STRING;
    }
}

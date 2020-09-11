package duke;

/**
 * Controls main logic of client-type user commands.
 */
abstract class ClientCommand {
    /**
     * Executes command.
     *
     * @param clients Stores client list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When date time in wrong format, or description not given, or I/O error.
     */
    abstract String execute(ClientList clients, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if should exit program.
     *
     * @return Whether or not to exit program.
     */
    abstract boolean isExit();
}

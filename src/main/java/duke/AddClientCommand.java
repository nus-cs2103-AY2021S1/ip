package duke;

/**
 * Controls logic of adding tasks.
 */
class AddClientCommand extends ClientCommand {

    /** Variable to store client name. */
    protected String name;
    /** Variable to store if client is male. */
    protected String isMale;
    /** Variable to store client birthday. */
    protected String birthday;

    AddClientCommand(String name, String isMale, String birthday) {
        this.name = name;
        this.isMale = isMale;
        this.birthday = birthday;
    }

    /**
     * Executes adding clients.
     *
     * @param clients Stores client list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When date time in wrong format, or description not given,
     */
    @Override
    String execute(ClientList clients, Ui ui, Storage storage) throws DukeException {
        String[] clientAndClientListInfo = clients.addClient(name, isMale, birthday);
        String clientString = clientAndClientListInfo[0];
        String lenString = clientAndClientListInfo[1];

        // TODO: Storage
        //ArrayList<Client> clientList = clients.getClients();
        //storage.save(clientList);
        return ui.showAddClientMessage(clientString, lenString);
    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}

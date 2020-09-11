package duke;

import java.util.ArrayList;

/**
 * Controls logic of listing clients.
 */
class ListClientCommand extends ClientCommand {

    /**
     * Executes list tasks.
     *
     * @param clients Stores client list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    String execute(ClientList clients, Ui ui, Storage storage) {
        ArrayList<String> clientListString = clients.listClients();
        return ui.showListClientMessage(clientListString);
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

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Contains client list, and has operations like list and add.
 */
class ClientList {

    /** List of tasks. */
    private ArrayList<Client> clientList = new ArrayList<>();

    /**
     * Constructs list of tasks.
     */
    ClientList() {
    }

    /**
     * Constructs list of tasks.
     *
     * @param clientStrings List of tasks in string format.
     * @throws DukeException  When date is in wrong format.
     */
//    ClientList(ArrayList<ArrayList<String>> clientStrings) throws DukeException {
//
//        for (ArrayList<String> clientString : clientStrings) {
//
//            String description = taskString.get(2);
//            int isDoneInt = Integer.parseInt(taskString.get(1));
//            boolean isDone = isDoneInt > 0;
//
//            String taskNameString = taskString.get(0);
//            TaskNameInStorage taskNameInStorage = TaskNameInStorage.valueOf(taskNameString);
//            switch (taskNameInStorage) {
//            case D:
//                handleDeadline(description, taskString.get(3), isDone);
//                break;
//            case E:
//                handleEvent(description, taskString.get(3), isDone);
//                break;
//            case T:
//                handleTodo(description, isDone);
//                break;
//            default:
//                throw new DukeException("Invalid character in storage file :-(");
//            }
//        }
//    }

    /**
     * Gets list of tasks.
     *
     * @return List of tasks.
     */
    ArrayList<Client> getClients() {
        return clientList;
    }

    String[] addClient(String name, String isMaleString,
                       String birthdayString) throws DukeException {
        try {
            LocalDate birthday = LocalDate.parse(birthdayString);
            boolean isMale = Boolean.parseBoolean(isMaleString);
            Client client = new Client(name, isMale, birthday);
            clientList.add(client);
            return getClientInfoAndClientListInfoForUi(client);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    String[] getClientInfoAndClientListInfoForUi(Client client) {
        int len = clientList.size();

        String[] clientAndClientListInfo = new String[2];
        clientAndClientListInfo[0] = client.toString();
        clientAndClientListInfo[1] = String.valueOf(len);
        return clientAndClientListInfo;
    }

    ArrayList<String> listClients() {
        ArrayList<String> clientListString = new ArrayList<>();
        for (Client client : clientList) {
            clientListString.add(client.toString());
        }
        return clientListString;
    }

    String[] deleteClient(int index) {
        Client client = clientList.get(index - 1);
        clientList.remove(index - 1);

        return getClientInfoAndClientListInfoForUi(client);
    }

}

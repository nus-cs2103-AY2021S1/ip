package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class FindtypeCommand implements Command {

    protected static final String USAGE = "findtype _T/D/E_";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        try {
            String type = text.substring(text.indexOf(' ') + 1);

            if (type.length() > 1) {
                throw new DobbyException("Incorrect usage of command. "
                        + "Please try again.\n  " + USAGE);
            }
            if (!(type.equalsIgnoreCase("T")
                    || type.equalsIgnoreCase("D")
                    || type.equalsIgnoreCase("E"))) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Type can be T, D, or E only. Please try again.\n  "
                        + USAGE);
            }

            message = tasks.findOfType(type.toUpperCase());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Type required cannot be empty. Please try again.\n  "
                    + USAGE);
        }
        return message;
    }
}

package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class FindCommand implements Command {

    protected static final String USAGE = "find _keyword_";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        try {
            String keyword = (text.substring(text.indexOf(' '))).substring(1);

            if (keyword.indexOf(' ') >= 0) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "You can only give a single word. Please try again.\n  "
                        + USAGE);
            } else if (keyword.length() == 0) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Keyword required cannot be empty. Please try again.\n  "
                        + USAGE);
            }

            message = tasks.findWithKeyword(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Keyword required cannot be empty. Please try again.\n  "
                    + USAGE);
        }
        return message;
    }
}

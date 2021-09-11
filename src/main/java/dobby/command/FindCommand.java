package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class FindCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("find") : "Find command must start with find";
        try {
            String keyword = (text.substring(text.indexOf(' '))).substring(1);

            if (keyword.indexOf(' ') >= 0) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "You can only give a single word. Please try again.");
            } else if (keyword.length() == 0) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Keyword required cannot be empty. Please try again.");
            }
            assert !keyword.equals(null) : "String to search for cannot be null.";

            message = tasks.findWithKeyword(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Keyword required cannot be empty. Please try again.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}

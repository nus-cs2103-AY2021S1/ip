package dobby.command;

import java.util.List;

import dobby.DobbyException;
import dobby.TaskList;

public class FindtypeCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("findtype") : "Findtype command must start with findtype";
        try {
            String type = text.substring(text.indexOf(' ') + 1);

            if (type.length() > 1) {
                throw new DobbyException("Incorrect usage of command. "
                        + "Please try again.");
            }

            type = type.toUpperCase();
            boolean isNotTaskType = !(type.equals("T") || type.equals("D") || type.equals("E"));
            if (isNotTaskType) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Type can be T, D, or E only. Please try again.");
            }
            assert List.of("T", "D", "E").contains(type) : "Type can be T, D, or E only";

            message = tasks.findOfType(type.toUpperCase());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Type required cannot be empty. Please try again.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}

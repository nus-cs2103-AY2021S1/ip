import java.util.List;

public class Parser {
    public static String[] parse(String command) {
        return command.split(" ", 2);
    }

    public static int getTaskIndex(String[] commands, List<Task> taskList) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("☹ OOPS!!! Please put a number to select a task for the \"" +
                    commands[0] + "\" action!");
        }
        try {
            int index = Integer.parseInt(commands[1]);
            Task task = taskList.get(index - 1);
            return index - 1;
        }  catch (NumberFormatException nfe) {
            throw new DukeException("☹ OOPS!!! Please input an actual number e.g. 1, 2, 3.");
        } catch (IndexOutOfBoundsException iobe) {
            throw new DukeException("☹ OOPS!!! Please select a valid task.");
        }
    }

    public static void checkValidAddCommand(String[] commands) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + commands[0] + " cannot be empty.");
        }
    }
}

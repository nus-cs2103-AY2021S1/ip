package duke;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    public static Command giveReminders() {
        return new ReminderCommand();
    }

    /**
     * Reads the user input and determines which command to execute.
     *
     * @param command User input.
     * @return Command The type of command to execute.
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.matches("^done\\s+\\d+$")) {
            int number = Integer.parseInt(command.split("\\s+")[1]) - 1;
            return new CompleteCommand(number);
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            if (command.length() <= 5 || command.substring(5).trim().isEmpty()) {
                throw new DukeException("Please enter the description of your todo");
            }
            return new AddCommand(TaskType.TODO, command.substring(5));
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            if (command.length() <= 9 || command.substring(9).trim().isEmpty()) {
                throw new DukeException("Please enter the description and deadline of your task");
            }
            String[] split = command.split("/by ");
            String description = split[0];
            String by = split[1];
            return new AddCommand(TaskType.DEADLINE, description, by);
        } else if (command.equals("event") || command.startsWith("event ")) {
            if (command.length() <= 6 || command.substring(6).trim().isEmpty()) {
                throw new DukeException("Please enter the description and timing of your event");
            }
            String[] split = command.split("/at ");
            String description = split[0];
            String at = split[1];
            return new AddCommand(TaskType.EVENT, description, at);
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            if (command.length() < 8 || command.substring(7).trim().isEmpty()) {
                throw new DukeException("Which task do you want to delete?");
            }
            String item = command.substring(7).trim();
            int number = Integer.parseInt(item) - 1;
            return new DeleteCommand(number);
        } else if (command.equals("find") || command.startsWith("find ")) {
            if (command.length() < 6 || command.substring(5).trim().isEmpty()) {
                throw new DukeException("Which task do you want to find?");
            }
            String keyword = command.substring(5).trim();
            return new FindCommand(keyword);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
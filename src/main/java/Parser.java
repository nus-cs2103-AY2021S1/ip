package main.java;

/**
 * This class consists of the parse method which parses user input and determines
 * the correct command for Bob to execute.
 */
public class Parser {

    /**
     * Returns the appropriate command for Bob to execute by parsing a provided String command by the user.
     *
     * @param command a String consisting of a command to be parsed for Bob to execute.
     * @return a command that corresponds to provided String command.
     * @throws BobException if a command cannot be parsed from the provided String command.
     */
    public static Command parse(String command) throws BobException {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.contains("done")) {

            int index;
            try {
                index = Integer.parseInt(command.substring(command.length() - 1));
            } catch (NumberFormatException e) {
                throw new BobNumberFormatException();
            }
            return new DoneCommand(index);
        } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
            Task task = null;
            if (command.contains("todo")) {
                try {
                    task = new Todo(command.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobNoDescriptionException();
                }
            } else if (command.contains("deadline")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(9, index).equals("")) {
                        throw new BobIncompleteDeadlineDescriptionException();
                    }
                    task = new Deadline(command.substring(9, index - 1), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobIncompleteDeadlineDescriptionException();
                }
            } else if (command.contains("event")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(6, index).equals("")) {
                        throw new BobIncompleteEventDescriptionException();
                    }
                    task = new Event(command.substring(6, index - 1), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobIncompleteEventDescriptionException();
                }
            }
            return new AddCommand(task);
        } else if (command.contains("delete")) {
            int index;
            try {
                index = Integer.parseInt(command.substring(command.length() - 1));
            } catch (NumberFormatException e) {
                throw new BobNumberFormatException();
            }
            return new DeleteCommand(index);
        } else if (command.contains("bye")) {
            return new ExitCommand();
        } else {
            throw new BobException();
        }
    }
}

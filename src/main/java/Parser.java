/**
 * Represents a parser that helps to make sense of the user commands.
 */
public class Parser {
    /**
     * Reads the user input and returns the corresponding command according to the command
     * given by the user.
     * @param input The user input into the program.
     * @return The corresponding command for the user input.
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        input = input.toLowerCase();
        String[] inputWords = input.split(" ");

        if (inputWords.length == 0) {
            throw new DukeException("Sorry, I don't understand what you are saying! D=\n"
                    + "Type \"help\" to view the list of commands you can use!");
        }

        String commandWord = inputWords[0];

        if (commandWord.equals("bye") && inputWords.length == 1) {
            return new ExitCommand();
        }

        if (commandWord.equals("help") && inputWords.length == 1) {
            return new HelpCommand();
        }

        if (commandWord.equals("list") && inputWords.length == 1) {
            return new ListCommand();
        }

        if (commandWord.equals("done")) {
            if (inputWords.length != 2) {
                throw new DukeException("You need to specify which task to mark done! \n"
                        + "eg done 1");
            }

            int i = input.charAt(5) - 48;
            return new DoneCommand(i);
        }

        if (commandWord.equals("delete")) {
            if (inputWords.length != 2) {
                throw new DukeException("You need to specify which task to delete!\n"
                        + "eg delete 1");
            }

            int i = input.charAt(7) - 48;
            return new DeleteCommand(i);
        }

        if (commandWord.equals("todo")) {
            if (inputWords.length == 1) {
                throw new DukeException("You need to specify your todo task!\n"
                        + "eg todo read book");
            }

            String taskDesc = input.substring(5);
            String[] taskInfos = {taskDesc};
            return new AddCommand(AddCommand.TODO, taskInfos);
        }

        if (commandWord.equals("deadline")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("You need to specify your deadline!\n"
                        + "eg deadline return book /by 2019-10-15 2359");
            }

            String taskDesc = input.substring(9);
            String[] taskInfos = taskDesc.split(" /by ");

            if (taskInfos.length == 1) {
                throw new DukeException("You need to use the proper format!\n"
                        + "eg deadline return book /by 2019-10-15 2359");
            }

            return new AddCommand(AddCommand.DEADLINE, taskInfos);
        }

        if (commandWord.equals("event")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("You need to specify your event!\n"
                        + "eg event project meeting /at 2019-10-15 1200");
            }

            String taskDesc = input.substring(6);
            String[] taskInfos = taskDesc.split(" /at ");

            if (taskInfos.length == 1) {
                throw new DukeException("You need to use the proper format!\n"
                        + "eg event project meeting /at 2019-10-15 1200");
            }

            return new AddCommand(AddCommand.EVENT, taskInfos);
        }

        throw new DukeException("Sorry, I don't understand what you are saying! D=\n"
                + "Type \"help\" to view the list of commands you can use!");

    }
}

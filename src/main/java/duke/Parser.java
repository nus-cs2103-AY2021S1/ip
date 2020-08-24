package duke;

/**
 * Responsible to make sense of user input in Duke and converts it to command.
 */
public class Parser {

    /**
     * Takes in a user query string, and interprets it into commands.
     *
     * @param query user input query.
     * @return Command instance.
     */
    public static Command parse(String query) throws DukeException {
        String[] queryArr = query.split(" ", 2);

        // String command = processArr[0];
        // if command is not list/bye: String input = processArr[1];

        try {
            switch (queryArr[0]) { // command
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(queryArr[1]) - 1);
            case "delete":
                return new DeleteCommand(Integer.parseInt(queryArr[1]) - 1);
            case "date":
                return new DateCommand(queryArr[1]);
            case "bye":
                return new ExitCommand();
            case "find":
                return new FindCommand(queryArr[1]);
            default:
                return new AddCommand(queryArr[0], queryArr[1]);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, which task number?");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :<");
        }
    }
}

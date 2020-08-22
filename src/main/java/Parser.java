public class Parser {

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
            default:
                return new AddCommand(queryArr[0], queryArr[1]);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, which task number?");
        }
    }
}

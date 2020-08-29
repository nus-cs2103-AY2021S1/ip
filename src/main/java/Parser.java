public class Parser {
    public static Command getCommand(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        String commandType = commandArr[0];

        switch (commandType) {
        case ("bye"):
            return new ByeCommand();
        case ("find"):
            return new FindCommand(commandArr[1]);
        case ("list"):
            return new ListCommand();
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(commandArr[1]));
        case ("done"):
            return new DoneCommand(Integer.parseInt(commandArr[1]));
        default:
            return new AddCommand(Parser.getNewTask(commandType, command, commandArr));
        }
    }

    /**
     * Returns an array of strings that make up the details of one task. Eg: ["T", "Read Book"].
     * @param addType The type of todo that is to be added.
     * @param inputArr A string array of input commands.
     * @return Details of a single task.
     * @throws DukeException If the "todo" command keyed by user does not contain a todo task string.
     */

    private static String[] getNewTask(String addType, String command, String[] inputArr) throws DukeException {
        String[] newTaskDetails;

        switch (addType) {
        case ("todo"):
            // Only has the word todo
            if (inputArr.length == 1) throw new EmptyTodoException();
            newTaskDetails = new String[]{"T", command.substring(5).trim()};
            break;
        case ("deadline"):
            String deadlineContent = command.substring(9);
            String[] deadlineArr = deadlineContent.split("/");
            newTaskDetails = new String[]{"D", deadlineArr[0].trim(), deadlineArr[1].substring(3).trim()};
            break;
        case("event"):
            String eventContent = command.substring(6);
            String[] eventArr = eventContent.split("/");
            newTaskDetails = new String[]{"E", eventArr[0].trim(), eventArr[1].substring(3).trim()};
            break;
        default:
            throw new DukeException();
        }
        return newTaskDetails;
    }
}

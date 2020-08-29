/**
 * Represent a parser to parse user input.
 */
public class Parser {
    /**
     * Return the output of Duke program after parsing the user input.
     * @param input user input
     * @param tasks task list
     * @param storage storage of the data of the program
     * @return output of the program as String
     * @throws DukeException exception thrown when input is invalid
     */
    public String parse(String input, TaskList tasks, Storage storage) throws DukeException {
        String output;
        String[] command = input.split(" ", 2);

        switch (command[0]) {
            case "bye":
                output = new ByeCommand().execute();
                break;
            case "list":
                output = new ListCommand(tasks).execute();
                break;
            case "done":
                output = new DoneCommand(command, tasks, storage).execute();
                break;
            case "todo":
                output = new ToDoCommand(command, tasks, storage).execute();
                break;
            case "deadline":
                output = new DeadlineCommand(command, tasks, storage).execute();
                break;
            case "event":
                output = new EventCommand(command, tasks, storage).execute();
                break;
            case "delete":
                output = new DeleteCommand(command, tasks, storage).execute();
                break;
            case "find":
                output = new FindCommand(command, tasks).execute();
                break;
            default:
                output = new ErrorCommand("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(").execute();
                break;
        }

        return output;
    }
}

package ParserStorageUi;
import Command.*;

public class Parser {

    /** The exit command **/
    private static String COMMAND_END = "bye";

    /** The command to set the specified task in the list to done **/
    private static String COMMAND_DONE = "done";

    /** The command to delete the specified task in the list **/
    private static String COMMAND_DELETE = "delete";

    /** The command to show the users their current list of task **/
    private static String COMMAND_LIST = "list";

    /** The command to find the task that matches the keyword in the list **/
    private static String COMMAND_FIND = "find";

    /**
     * @param fullCommand
     * @return the specified Command class
     */
    public static Command parse(String fullCommand){
        if (fullCommand.toLowerCase().contains(COMMAND_END)) {
            return new ExitCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(COMMAND_FIND)) {
            return new FindCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(COMMAND_LIST)) {
            return new ListCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(COMMAND_DONE)) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(COMMAND_DELETE)) {
            return new DeleteCommand(fullCommand);
        } else {
            return new AddCommand(fullCommand);
        }
    }
}

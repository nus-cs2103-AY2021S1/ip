package ParserStorageUi;

import Command.*;

public class Parser {

    /** The variable that will make the program exit or quit **/
    private static String end = "bye";

    /** The variable that will make the program to done the specified task **/
    private static String done = "done";

    /** The variable that will make the program to delete the specified task **/
    private static String delete = "delete";

    /** The variable that will make the program to list out all the current task **/
    private static String listing = "list";

    /**
     * @param fullCommand
     * @return the specified Command class
     */
    public static Command parse(String fullCommand){
        if (fullCommand.toLowerCase().contains(end)) {
            return new ExitCommand(fullCommand);
        } else if (fullCommand.equals(listing)) {
            return new ListCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(done)) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.toLowerCase().contains(delete)) {
            return new DeleteCommand(fullCommand);
        } else {
            return new AddCommand(fullCommand);
        }
    }
}

package ParserStorageUi;

import Command.*;

public class Parser {

    private static String end = "bye";
    private static String done = "done";
    private static String delete = "delete";
    private static String listing = "list";

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

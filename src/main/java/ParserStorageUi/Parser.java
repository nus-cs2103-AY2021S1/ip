package ParserStorageUi;
import Command.*;

public class Parser {

    private static String COMMAND_END = "bye";


    private static String COMMAND_DONE = "done";


    private static String COMMAND_DELETE = "delete";


    private static String COMMAND_LIST = "list";


    public static Command parse(String fullCommand){
        if (fullCommand.toLowerCase().contains(COMMAND_END)) {
            return new ExitCommand(fullCommand);
        } else if (fullCommand.equals(COMMAND_LIST)) {
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

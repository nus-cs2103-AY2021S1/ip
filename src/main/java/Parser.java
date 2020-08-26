//deals with making sense of the user command
public class Parser {
    
    public static Command parse(String input) throws PandaBotException{
        String[] cmd = input.split(" ", 2); // obtain first word and the rest of the string
        switch(cmd[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            verifyArguments(cmd, 2);
            try {
                int i = Integer.parseInt(cmd[1]) - 1;
                return new DoneCommand(i);
            } catch (NumberFormatException e) {
                throw new PandaBotInvalidArgumentFormatException(input);
            }
        case "delete":
            verifyArguments(cmd, 2);
            try {
                int j = Integer.parseInt(cmd[1]) - 1;
                return new DeleteCommand(j);
            } catch (NumberFormatException e) {
                throw new PandaBotInvalidArgumentFormatException(input);
            }
        case "todo":
            verifyArguments(cmd, 2);
            return new AddCommand(new ToDo(cmd[1]));
        case "deadline":
            verifyArguments(cmd,2);
            String[] deadlineDes = obtainDes(cmd[1], "/by ");
            return new AddCommand(new Deadline(deadlineDes[0], deadlineDes[1]));
        case "event":
            verifyArguments(cmd,2);
            String[] eventDes = obtainDes(cmd[1], "/at ");
            return new AddCommand(new Event(eventDes[0], eventDes[1]));
        default:
            throw new PandaBotInvalidCommandException();
        }
    }
    
    private static void verifyArguments(String[] cmd, int len) throws PandaBotInsufficientArgumentException{
        if (cmd.length < len) {
            throw new PandaBotInsufficientArgumentException();
        }
    }
    
    private static String[] obtainDes(String des, String separator) throws PandaBotInsufficientArgumentException {
        String[] res = des.split(separator, 2);
        verifyArguments(res, 2);
        return res;
    }
    
}

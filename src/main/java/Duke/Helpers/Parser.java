package Duke.Helpers;
import Duke.Commands.*;


/**
 * This is a Parser class that determines which command operation to choose, which in turn determines
 * the action to be taken.
 */
public class Parser {
    /**
     * This returns a Command, depending on the string being input
     * @param string where the first words determines command to be returned
     * @return Command is returned based on the first word of param string
     */
    public static Command parse(String string)  {
        if(string != null) {
            if (string.length() >= 3 && string.equals("bye")) {
                return new ExitCommand(string);
            } else if (string.length() >= 4 && string.equals("list")) {
                return new ListCommand(string);
            } else if (string.length() >= 6 && string.substring(0, 6).equals("delete")) {
                return new DeleteCommand(string);
            } else if (string.length() >= 4 && string.substring(0, 4).equals("done")) {
                return new DoneCommand(string);
            } else if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
                return new TodoCommand(string);
            } else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
                return new EventCommand(string);
            } else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
                return new DeadlineCommand(string);
            } else if (string.length() >= 4 && string.substring(0, 4).equals("find")) {
                return new FindCommand(string);
            } else {
                return new RandomCommand(string);
            }
        }else{
            return new ExitCommand(string);
        }
    }
}

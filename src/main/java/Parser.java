import task.Task;

/**
 * Parser is a utility class that helps to decode Duke text commands.
 */
public class Parser {
    /**
     * Utility method to extract time from the task
     */
    public static void parseTime(){

    }

    /**
     * Utility method to parse text commands from the user.
     * @param command   Command that the User has entered into Duke.
     * @return  A String array with the command being the first string and the task
     *          as the second string is there is a task.
     */
    public static String[] parseCommand(String command){
        return command.trim().split(" ",2);
    }
}

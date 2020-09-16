import java.util.Arrays;

public enum Command{
    INIT,
    LIST,
    BYE,
    DONE,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    ARCHIVE,
    INVALID;

    static private String originalCommand = ""; //originalCommand stores the original command (case sensitive)
    static private String action = "";
    static private String taskContent = "";
    Command(){}

//    Command(String command){
//        this.command = command.toUpperCase();
//        this.originalCommand = command;
//    }

    static String echo(){
        return originalCommand;
    }

    static String getTaskContent(){
        return taskContent;
    }

    static String getAction() { return action; }

    /**
     * Returns a Command object that enumerate original command
     *
     * @param command a string that contains the command
     * @return enumerated command
     */
    static Command getCommand(String command){
        taskContent = "";
        action = "";
        originalCommand = "";
        originalCommand = command;
        String[] splitedCommand = command.split(" ");
        action = splitedCommand[0];
        if (splitedCommand.length > 1) {
            taskContent = String.join(" ",
                    Arrays.copyOfRange(splitedCommand,
                                1,
                                splitedCommand.length)
            );
        }
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (Exception e){
            return Command.INVALID;
        }
    }
}
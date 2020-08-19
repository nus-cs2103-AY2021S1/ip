public enum Command{
    INIT,
    LIST,
    BYE,
    INVALID;

    static private String originalCommand = ""; //originalCommand stores the original command (case sensitive)

    Command(){}

//    Command(String command){
//        this.command = command.toUpperCase();
//        this.originalCommand = command;
//    }

    static String echo(){
        return originalCommand;
    }

    /**
     * Returns a Command object that enumerate original command
     *
     * @param command a string that contains the command
     * @return enumerated command
     */
    static Command getCommand(String command){
        originalCommand = command;
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (Exception e){
            return Command.INVALID;
        }
    }
}
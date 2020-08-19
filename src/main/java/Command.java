public enum Command{
    INIT,
    BYE,
    INVALID;

    static private String originalCommand = "";

    Command(){}

//    Command(String command){
//        this.command = command.toUpperCase();
//        this.originalCommand = command;
//    }

    static String echo(){
        return originalCommand;
    }

    static Command getCommand(String command){
        originalCommand = command;
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (Exception e){
            return Command.INVALID;
        }
    }
}
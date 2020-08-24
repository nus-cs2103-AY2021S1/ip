public enum DukeCommand {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    EXIT("bye");
    
    private String command;
    
    DukeCommand(String command) {
        this.command = command;
    }
    
    String getCommand() {
        return command;
    }
    
    static boolean equalsCommand(String userCommand, DukeCommand dukeCommand) {
        return dukeCommand.getCommand().equals(userCommand);
    }
}

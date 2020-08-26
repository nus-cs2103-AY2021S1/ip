package duke.command;

enum UserCommands {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), 
    DELETE("delete"), LIST("list"), DONE("done"), 
    FIND("find"), EXIT("bye");
    
    private final String commandWord;
    
    UserCommands(String commandWord) {
        this.commandWord = commandWord;
    }
    
    public String getCommandWord() {
        return this.commandWord;
    }
}

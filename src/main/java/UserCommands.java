enum UserCommands {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), 
    LIST("list"), DONE("done"), EXIT("bye");
    
    private final String commandWord;
    
    UserCommands(String commandWord) {
        this.commandWord = commandWord;
    }
    
    public String getCommandWord() {
        return this.commandWord;
    }
}

public enum CommandKey {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    EXIT("bye");
    
    private String key;
    
    CommandKey(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
    
    static boolean equalsCommandKey(String userCommand, CommandKey key) {
        return key.getKey().equals(userCommand);
    }
}

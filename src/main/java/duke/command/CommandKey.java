package duke.command;

public enum CommandKey {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    EXIT("bye"),
    FIND("find");
    
    private String key;
    
    CommandKey(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
    
    public static boolean equalsCommandKey(String userCommand, CommandKey key) {
        return key.getKey().equals(userCommand);
    }
}

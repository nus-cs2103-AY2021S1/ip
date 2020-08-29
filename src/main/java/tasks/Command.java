package tasks;

/**
 * Command is a Storage for the Enumerations of each type of command that is valid in Duke Chatbot
 */
public enum Command {
    error("asjdbaksjfbanfjknjkdfnskasd"),//random string as this is the default enum
    bye("bye"),
    help("help"),
    list("list"),
    done("done"),
    delete("delete"),
    todo("todo"),
    deadline("deadline"),
    event("event"),
    blank("");
    private final String code;
    private Command(String code){
        this.code = code;
    }

    /**
     * Getter for command that is encoded in a Command
     * @return exact text that represents a command in Duke
     */
    public String  getCode() {
        return code;
    }
    public String toString(){
        return code;
    }
}

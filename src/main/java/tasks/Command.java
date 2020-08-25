package tasks;

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

    public String  getCode() {
        return code;
    }
    public String toString(){
        return code;
    }
}

enum Commands {
    LIST("list"),
    BYE("bye"),
    DONE("done"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    DELETE("delete");

    private String action;

    public String getAction() {
        return this.action;
    }
    private Commands(String action) {
        this.action = action;
    }
};
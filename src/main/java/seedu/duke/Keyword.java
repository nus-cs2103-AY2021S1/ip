package seedu.duke;

public enum Keyword {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    FIND("find"),
    BYE("bye"),
    SORT("sort");


    public final String label;

    private Keyword(String label) {
        this.label = label;
    }

}

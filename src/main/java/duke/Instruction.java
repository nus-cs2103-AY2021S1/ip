package duke;

public enum Instruction {
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    LIST("list"),
    EVENT("event"),
    DEADLINE("deadline"),
    TODO("todo"),
    EMPTY("");

    private final String i;
    Instruction(String i) {
        this.i = i;
    }

    public String getInstruction() {
        return this.i;
    }
}

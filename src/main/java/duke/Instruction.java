package duke;

/**
 * Stores instructions given by user.
 */
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

    /**
     * Gets instructions.
     *
     * @return Instruction given by user.
     */
    public String getInstruction() {
        return this.i;
    }
}

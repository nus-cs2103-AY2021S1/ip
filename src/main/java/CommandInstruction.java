public enum CommandInstruction {
    LIST("list"),
    BYE("bye"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    VIEW("view");

    private final String instruction;

    CommandInstruction(String command) {
        this.instruction = command;
    }
}

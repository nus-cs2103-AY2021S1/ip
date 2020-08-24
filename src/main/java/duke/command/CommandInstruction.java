package duke.command;

public enum CommandInstruction {
    LIST("list"),
    BYE("bye"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    VIEW("view"),
    FIND("find");

    private final String instruction;

    CommandInstruction(String command) {
        this.instruction = command;
    }
}

package duke;

/**
 * Creates a parser which makes sense of the user input.
 */
public class Parser {
    /**
     * Returns a type of Instruction according to the user input.
     *
     * @param input The input from the user.
     * @return The type of instruction.
     */

    protected Instruction load(String input) {
        assert !input.isEmpty() : "input is empty";
        if (input.equals("list")) {
            return Instruction.LIST;
        } else if (input.startsWith("done")) {
            return Instruction.DONE;
        } else if (input.startsWith("delete")) {
            return Instruction.DELETE;
        } else if (input.startsWith("find")) {
            return Instruction.FIND;
        } else if (input.startsWith("deadline")) {
            return Instruction.DEADLINE;
        } else if (input.startsWith("event")) {
            return Instruction.EVENT;
        } else if (input.startsWith("todo")) {
            return Instruction.TODO;
        } else if (input.equals("bye")) {
            return Instruction.BYE;
        } else if (input.equals("undo")) {
            return Instruction.UNDO;
        } else {
            return Instruction.OTHERS;
        }
    }
}

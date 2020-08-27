package duke;

/**
 * Creates a parser which makes sense of the user input.
 */
public class Parser {
    /**
     * Returns a type of Instrcution according to the user input.
     *
     * @return The type of instruction.
     */
    public Instruction load(String input) {
        if (input.equals("list")) {
            return Instruction.LIST;
        } else if (input.startsWith("done")) {
            return Instruction.DONE;
        } else if (input.startsWith("delete")) {
            return Instruction.DELETE;
        } else if (input.startsWith("deadline")) {
            return Instruction.DEADLINE;
        } else if (input.startsWith("event")) {
            return Instruction.EVENT;
        } else if (input.startsWith("todo")) {
            return Instruction.TODO;
        } else {
            return Instruction.OTHERS;
        }
    }
}

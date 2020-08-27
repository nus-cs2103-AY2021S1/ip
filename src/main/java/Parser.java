public class Parser {
    protected Instruction load(String input) {
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

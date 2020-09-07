package duke;

/**
 * Parse user input.
 */
public class Parser {

    /**
     * Reads user input and invoke the Command based on the
     * input provided.
     *
     * @param userInput Input from user.
     * @return Returns appropriate Command.
     */
    static Command readUserInput(String userInput) {
        assert userInput != null;
        String[] inputArr = userInput.split(" ");
        String cmd = inputArr[0];
        if (cmd.equals(Instruction.BYE.getInstruction())) {
            return new ExitCommand();
        } else if (cmd.equals(Instruction.LIST.getInstruction())) {
            return new ListCommand();
        } else if (cmd.equals(Instruction.DONE.getInstruction())) {
            int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
            return new DoneCommand(itemsIdx);
        } else if (cmd.equals(Instruction.DELETE.getInstruction())) {
            int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
            return new DeleteCommand(itemsIdx);
        } else if (cmd.equals(Instruction.FIND.getInstruction())) {
            String keyword = inputArr[1];
            return new FindCommand(keyword);
        } else {
            return new AddCommand(userInput);
        }
    }
}

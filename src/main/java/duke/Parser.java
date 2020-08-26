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
        if (userInput.equals(Instruction.BYE.getInstruction())) {
            return new ExitCommand();
        } else if (userInput.equals(Instruction.LIST.getInstruction())) {
            return new ListCommand();
        } else {
            String[] inputArr = userInput.split(" ");
            String taskName = inputArr[0];
            if (taskName.equals(Instruction.DONE.getInstruction())) {
                int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                return new DoneCommand(itemsIdx);
            } else if (taskName.equals(Instruction.DELETE.getInstruction())) {
                int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                return new DeleteCommand(itemsIdx);
            } else if (taskName.equals(Instruction.FIND.getInstruction())) {
                String keyword = inputArr[1];
                return new FindCommand(keyword);
            } else {
                return new AddCommand(userInput);
            }
        }
    }
}

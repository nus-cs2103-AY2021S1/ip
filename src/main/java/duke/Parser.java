package duke;

public class Parser {

    static Command readUserInput(String userInput) {
        if (userInput.equals(Instruction.BYE.getInstruction())) {
            return new ExitCommand();
        } else if (userInput.equals(Instruction.LIST.getInstruction())) {
            return new ListCommand();
        } else {
            String[] inputArr = userInput.split(" ");
            if (inputArr[0].equals(Instruction.DONE.getInstruction())) {
                int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                return new DoneCommand(itemsIdx);
            } else if (inputArr[0].equals(Instruction.DELETE.getInstruction())) {
                int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                return new DeleteCommand(itemsIdx);
            } else {
                return new AddCommand(userInput);
            }
        }
    }
}
